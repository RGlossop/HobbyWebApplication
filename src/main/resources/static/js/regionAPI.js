//Buttons
const getRegions = document.querySelector('#getRegions');
const getRegionByID = document.querySelector('#getRegionID');
const deleteRegionByID = document.querySelector('#deleteRegionById');
const createRegion = document.querySelector('#createRegion');
const updateRegion = document.querySelector('#updateRegion');
//forms
const readRegionID = document.querySelector('#readRegionID');
const deleteRegionID = document.querySelector('#deleteRegionID');
//CREATE form
const createRegionName = document.querySelector('#regionName');
const createRegionDescription = document.querySelector('#descripton');
//UPDATE form
const upRegionID = document.querySelector('#upRegionID');
const upRegionName = document.querySelector('#upRegionName');
const upRegionDescription = document.querySelector('#upRegionDescription');
//status Divs
const regionList = document.querySelector('#regionList');
const deleteStatus = document.querySelector('#deleteStatus');
const createStatus = document.querySelector('#createStatus');
const updateStatus = document.querySelector('#updateStatus');

const retrieveRegions = () => {
    regionList.innerHTML = "";
    fetch("http://localhost:8080/region/readAll")
    .then(response => response.json())
    .then(json => {
        console.log(json);
        console.log(typeof json)
        for(let i=0; i<json.length; i++) {
            let br = document.createElement('p');
            let brText = document.createTextNode("¦============================================¦");
            br.appendChild(brText);
            regionList.appendChild(br);

            let h2 = document.createElement("h2");
            let name = document.createTextNode(json[i].name);
            let h3 = document.createElement("h3");
            let role = document.createTextNode(json[i].description);
            h2.appendChild(name);
            h3.appendChild(role);
            regionList.appendChild(h2);
            regionList.appendChild(h3)
            for(let x=0; x<json[i].champList.length; x++){
                let h4 = document.createElement("h4");
                let p = document.createElement("p");
                let br = document.createElement("p");
                let champName = document.createTextNode(json[i].champList[x].name); 
                let champRole = document.createTextNode(json[i].champList[x].role);
                let brText = document.createTextNode("================================");
                h4.appendChild(champName);
                p.appendChild(champRole);
                br.appendChild(brText);
                regionList.appendChild(br);
                regionList.appendChild(h4);
                regionList.appendChild(p);
            }
        }
    });
}

const retrieveRegion = () => {
    regionList.innerHTML = "";
    let fetchString = "http://localhost:8080/region/read/";
    let readRegionIdNum = Number.parseFloat(readRegionID.value);
    fetchString +=readRegionIdNum;
    fetch(fetchString)
    .then(response => response.json())
    .then(json => {
        console.log(json);
        console.log(typeof json);
        if(json.name !=undefined) {
            for(let x=0; x<json.champList.length; x++){
                let h4 = document.createElement("h4");
                let p = document.createElement("p");
                let br = document.createElement("p");
                let champName = document.createTextNode(json.champList[x].name); 
                let champRole = document.createTextNode(json.champList[x].role);
                let brText = document.createTextNode("================================");
                h4.appendChild(champName);
                p.appendChild(champRole);
                br.appendChild(brText);
                regionList.appendChild(h4);
                regionList.appendChild(p);
                regionList.appendChild(br);
            }
        } else {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("Id doesnt exist");
            h3.appendChild(errorText);
            regionList.appendChild(h3);
        }
    })
}
const removeRegion = () => {
    deleteStatus.innerHTML = "";
    let fetchString = "http://localhost:8080/region/delete/"
    let regionIdNum = Number.parseFloat(deleteRegionID.value);
    fetchString += regionIdNum;
    fetch(fetchString, {
        method: 'DELETE',
    })
    .then((response) => {
        if(response.ok) {
            let h3 = document.createElement("h3");
            let successText = document.createTextNode("Region Deleted");
            h3.appendChild(successText);
            deleteStatus.appendChild(h3);
        } else {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("Id doesnt exist");
            h3.appendChild(errorText);
            deleteStatus.appendChild(h3);
        }
    });
}

const addRegion = () => {
    createStatus.innerHTML = "";
    let regionName = createRegionName.value;
    let regionDescrion = createRegionDescription.value;
    fetch("http://localhost:8080/region/create", {
        method: 'POST',
        body: JSON.stringify({
            "name": regionName,
            "description": regionDescrion
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
    .then((response) => {
        if(response.ok) {
            let h3 = document.createElement("h3");
            let successText = document.createTextNode("Region Created");
            h3.appendChild(successText);
            createStatus.appendChild(h3);
        } else {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("error creating region");
            h3.appendChild(errorText);
            createStatus.appendChild(h3);
        }
    });
}

const alterRegion = ()  => {

    updateStatus.innerHTML = "";
    let fetchString = "http://localhost:8080/champion/update/";
    let regionId = Number.parseFloat(upRegionID.value);
    fetchString += regionId;
    if(regionId <= 0) {
        let h3 = document.createElement("h3");
        let errorText = document.createTextNode("error updating region, please ensure region ID is correct");
        h3.appendChild(errorText);
        updateStatus.appendChild(h3);
    } else if(upRegionName.value == "") {
        let h3 = document.createElement("h3");
        let errorText = document.createTextNode("error updating region, please ensure a name is entered");
        h3.appendChild(errorText);
        updateStatus.appendChild(h3);
    } else if(upRegionDescription.value == "") {
        let h3 = document.createElement("h3");
        let errorText = document.createTextNode("error updating region, please ensure a description is entered");
        h3.appendChild(errorText);
        updateStatus.appendChild(h3);
    } else {
        fetch(fetchString, {
            method: 'PUT',
            body: JSON.stringify({
                "id" : regionId,
                "name": upRegionName.value,
                "description": upRegionDescription.value
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8'
            },
        })
        .then((response) => {
            if(response.status == 202) {
                let h3 = document.createElement("h3");
                let successText = document.createTextNode("region Updated");
                h3.appendChild(successText);
                updateStatus.appendChild(h3);
            } else {
                let h3 = document.createElement("h3");
                let successText = document.createTextNode("error updating region, please ensure region ID is correct");
                h3.appendChild(successText);
                updateStatus.appendChild(h3);
            }
        });
    }
}
createRegion.addEventListener('click', addRegion);
getRegions.addEventListener('click', retrieveRegions);
getRegionByID.addEventListener('click', retrieveRegion);
updateRegion.addEventListener('click', alterRegion);
deleteRegionByID.addEventListener('click', removeRegion);
//Buttons
const getRegions = document.querySelector('#getRegions');
const getRegionByID = document.querySelector('#getRegionID');
const deleteRegionByID = document.querySelector('#deleteRegionById');
const createRegion = document.querySelector('#createRegion');
//forms
const readRegionID = document.querySelector('#readRegionID');
const deleteRegionID = document.querySelector('#deleteRegionID');
//CREATE form
const createRegionName = document.querySelector('#regionName');
const createRegionDescription = document.querySelector('#descripton');

//status Divs
const regionList = document.querySelector('#regionList');
const deleteStatus = document.querySelector('#deleteStatus');
const createStatus = document.querySelector('#createStatus');

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

}
createRegion.addEventListener('click', addRegion);
getRegions.addEventListener('click', retrieveRegions);
getRegionByID.addEventListener('click', retrieveRegion);
deleteRegionByID.addEventListener('click', removeRegion);
//Buttons
const getChamps = document.querySelector('#getChamps');
const getChampID = document.querySelector('#getChampById');
const deleteChampByID = document.querySelector('#deleteChampById');
const createChamp = document.querySelector('#createChamp');
//forms
const readChampID = document.querySelector('#readChampID');
const delChampID = document.querySelector('#deleteChampID');

//createForm
const createChampName = document.querySelector('#champName');
const createChampRole = document.querySelector('#champRole');
const createChampRegion = document.querySelector('#champRegionID');

// update divs
const champList = document.querySelector('#champList');
const deleteStatus = document.querySelector('#deleteStatus');
const createStatus = document.querySelector('#createStatus');

const retrieveChamps = () => {
    champList.innerHTML = "";
    fetch("http://localhost:8080/champion/readAll")
    .then(response => response.json())
    .then(json => {
        console.log(json);
        console.log(typeof json);
        for(let i=0; i<json.length; i++) {
            let h3 = document.createElement("h3");
            let name = document.createTextNode(json[i].name);
            let p = document.createElement("p");
            let role = document.createTextNode(json[i].role);
            
            h3.appendChild(name);
            p.appendChild(role);

            champList.appendChild(h3);
            champList.appendChild(p)
        }
    })
}
const retrieveChamp = () => {
    champList.innerHTML = "";
    let fetchString = "http://localhost:8080/champion/read/";
    let champIdNum = Number.parseFloat(readChampID.value);
    fetchString += champIdNum;
    fetch(fetchString)
    .then(response => response.json())
    .then(json => {
        console.log(json);
        console.log(typeof json);
        if(json.name != undefined) {
            let h3 = document.createElement("h3");
            let name = document.createTextNode(json.name);
            let p = document.createElement("p");
            let role = document.createTextNode(json.role);
            h3.appendChild(name);
            p.appendChild(role);
            champList.appendChild(h3);
            champList.appendChild(p)
        } else {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("Id doesnt exist");
            h3.appendChild(errorText);
            champList.appendChild(h3);
        }
    })
}

const deleteChamp = () => {
    deleteStatus.innerHTML = "";
    let fetchString = "http://localhost:8080/champion/delete/"
    let champIdNum = Number.parseFloat(delChampID.value);
    fetchString += champIdNum;
    fetch(fetchString, {
        method: 'DELETE',
    })
    .then((response) => {
        if(response.ok) {
            let h3 = document.createElement("h3");
            let successText = document.createTextNode("Champ Deleted");
            h3.appendChild(successText);
            deleteStatus.appendChild(h3);
        } else {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("Id doesnt exist");
            h3.appendChild(errorText);
            deleteStatus.appendChild(h3);
        }
    })
    //.then((json) => console.log(json));
}
const addChamp = () => {
    createStatus.innerHTML = "";
    let champName = createChampName.value;
    let champRole = createChampRole.value;
    let regionID = Number.parseFloat(createChampRegion.value)
    fetch("http://localhost:8080/champion/create", {
        method: 'POST',
        body: JSON.stringify({
            "name": champName,
            "role": champRole,
            "region" : {
                "id" : regionID
            }
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
    .then((response) => {
        if(response.status == 500) {
            let h3 = document.createElement("h3");
            let errorText = document.createTextNode("error creating champ, please ensure region ID is correct");
            h3.appendChild(errorText);
            createStatus.appendChild(h3);
        } else if (response.ok) {
            let h3 = document.createElement("h3");
            let successText = document.createTextNode("Champ Created");
            h3.appendChild(successText);
            createStatus.appendChild(h3);
        }
    })
}  
    

getChamps.addEventListener('click', retrieveChamps);
getChampID.addEventListener('click', retrieveChamp);
deleteChampByID.addEventListener('click', deleteChamp);
createChamp.addEventListener('click', addChamp);
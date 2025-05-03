const dropArea = document.getElementById("drop-area");
const inputFile = document.getElementById("input-file");
const imageView = document.getElementById("img-view");
const form = document.querySelector("form");

inputFile.addEventListener("change", uploadImage);

//aoip bean
function uploadImage(){
    let imgLink = URL.createObjectURL(inputFile.files[0])
    imageView.style.backgroundImage = `url(${imgLink})` ;
    imageView.textContent = "";
    imageView.style.border = 0;
}

//holly beeboop
form.addEventListener("submit", async function (e) {
    e.preventDefault(); 
  
    const idolName = document.querySelector('#iname').value;
    const groupName = document.querySelector('#gname').value;
    const setName = document.querySelector('#set').value;
    const file = inputFile.files[0];
  
    if (!file || file.type !== "image/png") {
      alert("Please upload a valid PNG file.");
      return;
    }
  
    const info = {
      artistName: idolName,
      groupName: groupName,
      set: setName,
      lookingForList: [] 
    };
  
    const formData = new FormData();
    formData.append("image", file);
    formData.append("info", new Blob([JSON.stringify(info)], { type: "application/json" }));
  
    try {
      const response = await fetch("http://localhost:8080/api/photocards/upload", {
        method: "POST",
        body: formData
      });
  
      if (!response.ok) {
        const errText = await response.text();
        throw new Error(errText);
      }
  
      const result = await response.text();
      alert(result);
    } catch (err) {
      alert("Upload failed: " + err.message);
    }
  });
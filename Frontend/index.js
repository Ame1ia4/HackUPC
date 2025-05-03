window.addEventListener("DOMContentLoaded", async () => {
    const container = document.querySelector(".flex.overflow-x-auto");
  
    try {
      const res = await fetch("http://localhost:8080/api/photocards");
      const cards = await res.json();
  
      container.innerHTML = ""; 
  
      cards.forEach(card => {
        const div = document.createElement("div");
        div.className = "card flex-shrink-0 w-64 h-64 bg-white rounded-lg shadow-md flex flex-col items-center justify-center p-4";
  
        const img = document.createElement("img");
        img.src = `http://localhost:8080/uploads/${card.imagePath}`;
        img.alt = card.artistName;
        img.className = "w-full h-40 object-cover rounded";
  
        const name = document.createElement("h3");
        name.textContent = `${card.artistName} (${card.groupName})`;
        name.className = "mt-2 text-sm font-medium text-center";
  
        const set = document.createElement("p");
        set.textContent = `Set: ${card.set}`;
        set.className = "text-xs text-gray-500";
  
        div.appendChild(img);
        div.appendChild(name);
        div.appendChild(set);

        const lookingFor = document.createElement("ul");
    lookingFor.className = "text-xs text-gray-600 mt-2";
    lookingFor.textContent = "Looking For:";

    card.lookingForList.forEach(trade => {
        const li = document.createElement("li");
        li.textContent = `${trade.artistName || "any"} / ${trade.groupName || "any"} / ${trade.set || "any"}`;
        lookingFor.appendChild(li);
    });

div.appendChild(lookingFor);
  
        container.appendChild(div);
      });
  
    } catch (err) {
      console.error("Failed to load photocards:", err);
    }
  });
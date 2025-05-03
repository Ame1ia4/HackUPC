async function fetchCards() {
    const res = await fetch("http://localhost:8080/api/photocards");
    return await res.json();
  }
  
  function renderCards(cards) {
    const container = document.getElementById("card-container");
    container.innerHTML = "";
  
    cards.forEach(card => {
      const div = document.createElement("div");
      div.className = "card";
  
      const img = document.createElement("img");
      img.src = `http://localhost:8080/uploads/${card.imagePath}`;
      img.alt = card.artistName;
  
      const title = document.createElement("h3");
      title.textContent = `${card.artistName} (${card.groupName})`;
  
      const set = document.createElement("p");
      set.textContent = `Set: ${card.set}`;
  
      const looking = document.createElement("p");
      looking.className = "looking-for";
      const wishes = card.lookingForList.map(lf =>
        [lf.artistName, lf.groupName, lf.set].filter(Boolean).join(" / ")
      ).join(" | ");
      looking.textContent = `Looking For: ${wishes || "Anything"}`;
  
      div.appendChild(img);
      div.appendChild(title);
      div.appendChild(set);
      div.appendChild(looking);
  
      container.appendChild(div);
    });
  }
  
  function setupSearch(cards) {
    const input = document.getElementById("search");
    input.addEventListener("input", () => {
      const query = input.value.toLowerCase();
      const filtered = cards.filter(card =>
        card.artistName?.toLowerCase().includes(query) ||
        card.groupName?.toLowerCase().includes(query) ||
        card.set?.toLowerCase().includes(query)
      );
      renderCards(filtered);
    });
  }
  
  window.addEventListener("DOMContentLoaded", async () => {
    const cards = await fetchCards();
    renderCards(cards);
    setupSearch(cards);
  });
  
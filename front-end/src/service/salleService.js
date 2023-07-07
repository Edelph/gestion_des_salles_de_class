import { API } from "./config";

class SalleService {
  getSalles() {
    return fetch(`${API}/salles`);
  }
  getSalle(id) {
    return fetch(`${API}/salles/${id}`);
  }
  addSalle(salle) {
    return fetch(`${API}/salles`, {
      method: "POST",
      body: JSON.stringify(salle),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.json());
  }
  updateSalle(id, salle) {
    return fetch(`${API}/salles/${id}`, {
      method: "PUT",
      body: JSON.stringify(salle),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

const salleService = new SalleService();
export default salleService;

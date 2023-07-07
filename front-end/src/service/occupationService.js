import { API } from "./config";

class OccupationService {
  getOccupations() {
    return fetch(`${API}/occupes`);
  }
  getOccupation(id) {
    return fetch(`${API}/occupes/${id}`);
  }
  addOccupation(occuper) {
    return fetch(`${API}/occupes`, {
      method: "POST",
      body: JSON.stringify(occuper),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.json());
  }
  updateOccupation(id, occuper) {
    return fetch(`${API}/occupes/${id}`, {
      method: "PUT",
      body: JSON.stringify(occuper),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

const occupService = new OccupationService();
export default occupService;

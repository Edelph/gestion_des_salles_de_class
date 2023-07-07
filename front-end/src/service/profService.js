import { API } from "./config";

class ProfService {
  getProfs() {
    return fetch(`${API}/professeurs`);
  }
  getProf(id) {
    return fetch(`${API}/professeurs/${id}`);
  }
  addProf(prof) {
    return fetch(`${API}/professeurs`, {
      method: "POST",
      body: JSON.stringify(prof),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.json());
  }
  updateProf(id, prof) {
    return fetch(`${API}/grades/${id}`, {
      method: "PUT",
      body: JSON.stringify(prof),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

const profService = new ProfService();
export default profService;

import { API } from "./config";

class GradeService {
  getGrades() {
    return fetch(`${API}/grades`);
  }
  getGrade(id) {
    return fetch(`${API}/grades/${id}`);
  }
  addGrade(grade) {
    return fetch(`${API}/grades`, {
      method: "POST",
      body: JSON.stringify(grade),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((res) => res.json());
  }
  updateGrade(id, grade) {
    return fetch(`${API}/grades/${id}`, {
      method: "PUT",
      body: JSON.stringify(grade),
      headers: {
        "Content-Type": "application/json",
      },
    });
  }
}

const gradeService = new GradeService();
export default gradeService;

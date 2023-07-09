import "./App.css";

//theme
import "primereact/resources/themes/lara-light-indigo/theme.css";

//core
import "primereact/resources/primereact.min.css";
import MyNavbar from "./components/nav_bar";
import "bootstrap/dist/css/bootstrap.min.css";
import GradePage from "./pages/grade-page";
import { Container, Row } from "react-bootstrap";
import SallePage from "./pages/salle-page";
import ProfPage from "./pages/prof-page";
import OccupePage from "./pages/occupe-page";
import FormOccup from "./pages/form-occupe";

function App() {
  return (
    <>
      <MyNavbar />
      <Container>
        <GradePage />
        <SallePage />
         <ProfPage />
        <OccupePage />
        <FormOccup />
      </Container>
    </>
  );
}

export default App;

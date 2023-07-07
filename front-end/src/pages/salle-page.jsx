import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Container,
  Form,
  InputGroup,
  ListGroup,
  Stack,
} from "react-bootstrap";
import MyModal from "../components/modal";
import ModalSupp from "../components/ModalSupp";
import salleService from "./../service/salleService";

const SallePage = () => {
  const [grades, setGrades] = useState([]);
  const [search, setSearch] = useState("");
  const [selectedGrade, setSelectedGrade] = useState("");
  const [load, setLoad] = useState(false);
  const [id, setId] = useState(null);
  const [show, setShow] = useState(false);
  const [showSupp, setShowSupp] = useState(false);
  useEffect(() => {
    getGrades();
  }, []);

  const getGrades = async () => {
    const response = await salleService.getSalles();
    if (response.status === 200) setGrades([...response.data]);
  };

  const addGrade = async () => {
    if (selectedGrade === "") return;
    setLoad(true);
    if (id) {
      const response = await salleService.updateSalle(id, {
        designation: selectedGrade,
      });
      if (response.status === 200) {
        const index = grades.findIndex((grade) => grade.codeSal === id);
        const newGrades = grades[index];
        newGrades.designation = selectedGrade;
        grades[index] = newGrades;
        setGrades([...grades]);
        setShow(false);
        setLoad(false);
      }
    } else {
      const response = await salleService.addSalle({
        designation: selectedGrade,
      });
      if (response.status === 200) {
        setSelectedGrade("");
        setGrades((grades) => [response.data, ...grades]);
        setLoad(false);
      }
    }
  };
  const suppGrade = async () => {
    setLoad(true);
    const response = await salleService.deleteSalle(id);
    if (response.status === 200) {
      const Ngrades = grades.filter((grade) => grade.codeSal !== id);
      setGrades([...Ngrades]);
      setShowSupp(false);
      setLoad(false);
    }
  };

  const searchHandle = async (value) => {
    setSearch(value);
    if (value === "") getGrades();
    else {
      const response = await salleService.filter(value);
      if (response.status === 200) {
        setGrades([...response.data]);
      }
    }
  };

  return (
    <>
      <ModalSupp
        setShow={setShowSupp}
        onLoad={load}
        show={showSupp}
        onOk={suppGrade}
      />
      <MyModal
        onOk={addGrade}
        onLoad={load}
        setShow={setShow}
        show={show}
        title="Ajouter une Grade"
      >
        <Form>
          <Form.Group className="mb-3">
            <Form.Label>Nom Salle : </Form.Label>
            <Form.Control
              value={selectedGrade}
              onChange={(e) => setSelectedGrade(e.target.value)}
              type="email"
              placeholder="Salle Rose"
            />
          </Form.Group>
        </Form>
      </MyModal>
      <Container className="mt-4">
        <h1 className="text-center"> CRUD Salle de classe</h1>
        <Stack className="mx-4 py-4 px-4" direction="horizontal" gap={3}>
          <div className="p-2">
            <Button
              onClick={(e) => {
                setId(null);
                setShow(true);
              }}
              variant="primary"
            >
              Ajouter
            </Button>
          </div>
          <div className="p-2 ms-auto">
            <InputGroup>
              <InputGroup.Text id="basic-addon1">Search</InputGroup.Text>
              <Form.Control
                onChange={(e) => searchHandle(e.target.value)}
                aria-label="Username"
                value={search}
                aria-describedby="basic-addon1"
              />
            </InputGroup>
          </div>
        </Stack>
        <ListGroup className="mx-4 px-4">
          {grades.map((grade, index) => (
            <ListGroup.Item key={index}>
              <Stack direction="horizontal" gap={3}>
                <h5 className="me-auto">{grade?.designation}</h5>
                <Button
                  onClick={(e) => {
                    setShow(true);
                    setId(grade.codeSal);
                    setSelectedGrade(grade?.designation);
                  }}
                  className="fs-15"
                  variant="secondary"
                >
                  modifier
                </Button>
                <div className="vr" />
                <Button
                  onClick={(e) => {
                    setId(grade.codeSal);
                    setShowSupp(true);
                  }}
                  variant="outline-danger"
                >
                  supprimer
                </Button>
              </Stack>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </Container>
    </>
  );
};
export default SallePage;

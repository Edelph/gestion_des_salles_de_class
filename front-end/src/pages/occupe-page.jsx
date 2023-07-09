import React, { useEffect, useRef, useState } from "react";
import {
  Button,
  Container,
  Form,
  InputGroup,
  ListGroup,
  Stack,
} from "react-bootstrap";
import ModalSupp from "../components/ModalSupp";
import occupService from "../service/occupationService";

const OccupePage = () => {
  const [occupations, setOccupations] = useState([]);
  const [search, setSearch] = useState("");
  const [selectedOcc, setSelectedOcc] = useState({});
  const [load, setLoad] = useState(false);

  const [showSupp, setShowSupp] = useState(false);
  useEffect(() => {
    getOccupes();
  }, []);

  const getOccupes = async () => {
    const response = await occupService.getOccs();
    if (response.status === 200) setOccupations([...response.data]);
  };

  const suppGrade = async () => {
    setLoad(true);
    const response = await occupService.deleteGrade(selectedOcc.codeOcc);
    if (response.status === 200) {
      const Ngrades = occupations.filter(
        (occ) => occ.codeOcc !== selectedOcc.codeOcc
      );
      setOccupations([...Ngrades]);
      setShowSupp(false);
      setLoad(false);
    }
  };

  const searchHandle = async (value) => {
    setSearch(value);
    if (value === "") getOccupes();
    else {
      const response = await occupService.filter(value);
      if (response.status === 200) {
        setOccupations([...response.data]);
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
      <Container className="mt-4">
        <h1 className="text-center"> CRUD Occupation</h1>
        <Stack className="mx-4 py-4 px-4" direction="horizontal" gap={3}>
          <div className="p-2">
            <Button variant="primary">Ajouter</Button>
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
          {occupations.map((occ, index) => (
            <ListGroup.Item key={index}>
              <Stack direction="horizontal" gap={3}>
                <h5 className="me-auto">
                  {occ?.professeur.name} {occ?.salle.codeSal}
                </h5>
                <Button className="fs-15" variant="secondary">
                  modifier
                </Button>
                <div className="vr" />
                <Button variant="outline-danger">supprimer</Button>
              </Stack>
            </ListGroup.Item>
          ))}
        </ListGroup>
      </Container>
    </>
  );
};
export default OccupePage;

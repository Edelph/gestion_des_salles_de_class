import React, { useState } from "react";
import {
  Button,
  Col,
  Container,
  Form,
  ListGroup,
  Row,
  Stack,
} from "react-bootstrap";
import ModalFormProf from "./modal-form-Prof";
import ModalFormSalle from "./modal-form-salle";
import occupService from "../service/occupationService";

const FormOccup = () => {
  const [selectedOcc, setSelectedOcc] = useState({});
  const [selectedProf, setSelectedProf] = useState({});
  const [selectedSalle, setSelectedSalle] = useState({});
  const [showModalSalle, setShowModalSalle] = useState(false);
  const [showModalProf, setShowModalProf] = useState(false);
  const [load, setLoad] = useState(false);

  const addOcc = async () => {
    setLoad(true);
    const newOcc = {
      ...selectedOcc,
      professeur: { ...selectedProf },
      salle: { ...selectedSalle },
    };
    console.log(selectedOcc);
    if (newOcc === "") return;

    if (newOcc.codeOcc) {
      const response = await occupService.updateOcc(newOcc.codeOcc, {
        ...newOcc,
      });
      if (response.status === 200) {
        setSelectedOcc({});
        setLoad(false);
      }
    } else {
      const response = await occupService.addOcc({ ...newOcc });
      if (response.status === 200) {
        setSelectedOcc({});
        setLoad(false);
      }
    }
  };

  return (
    <>
      <ModalFormProf
        onOk={setSelectedProf}
        selected={selectedProf}
        onLoad={load}
        setShow={setShowModalProf}
        show={showModalProf}
      />
      <ModalFormSalle
        onOk={setSelectedSalle}
        selected={selectedSalle}
        onLoad={load}
        setShow={setShowModalSalle}
        show={showModalSalle}
      />

      <div style={{ width: "500px", marginBottom: "3rem" }}>
        <Form>
          <Container>
            <Form.Group className="mb-3">
              <Form.Label>date : </Form.Label>
              <Form.Control
                value={selectedOcc?.dateOccupe || ""}
                onChange={(e) => {
                  setSelectedOcc((occ) => {
                    return { ...occ, dateOccupe: e.target.value };
                  });
                }}
                type="date"
                placeholder="Doctorat"
              />
            </Form.Group>
          </Container>
          <Container>
            <Form.Label>Heur d'occupation </Form.Label>
            <Row>
              <Col>
                <Form.Group className="mb-3">
                  <Form.Label>Debut : </Form.Label>
                  <Form.Control
                    value={selectedOcc?.startTime || ""}
                    onChange={(e) => {
                      setSelectedOcc((occ) => {
                        return { ...occ, startTime: e.target.value };
                      });
                    }}
                    type="time"
                    placeholder="Doctorat"
                  />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group className="mb-3">
                  <Form.Label>Fin : </Form.Label>
                  <Form.Control
                    value={selectedOcc?.endTime || ""}
                    onChange={(e) => {
                      setSelectedOcc((occ) => {
                        return { ...occ, endTime: e.target.value };
                      });
                    }}
                    type="time"
                    placeholder="Doctorat"
                  />
                </Form.Group>
              </Col>
            </Row>
          </Container>
          <Container>
            <Form.Label>Prof et Salle : </Form.Label>
            <Row>
              <Col>
                <Button onClick={(e) => setShowModalProf(true)}>
                  Proffesseur
                </Button>
              </Col>
              <Col>
                <Button onClick={(e) => setShowModalSalle(true)}>Salle</Button>
              </Col>
            </Row>
            <Row>
              <Col>
                {selectedProf && (
                  <ListGroup className="mt-2">
                    <ListGroup.Item>
                      <Stack direction="horizontal" gap={3}>
                        <div className="me-auto">
                          <h6>{selectedProf?.name}</h6>
                          <i> {selectedProf?.grade?.designation} </i>
                          <em> || {selectedProf?.genre}</em>
                        </div>
                      </Stack>
                    </ListGroup.Item>
                  </ListGroup>
                )}
              </Col>
              <Col>
                {selectedSalle && (
                  <ListGroup className="mt-2">
                    <ListGroup.Item>
                      <Stack direction="horizontal" gap={3}>
                        <div className="me-auto">
                          <h6>{selectedSalle?.designation}</h6>
                        </div>
                      </Stack>
                    </ListGroup.Item>
                  </ListGroup>
                )}
              </Col>
            </Row>
          </Container>
          <Stack
            direction="horizontal"
            className="mt-4 justify-content-end"
            gap={3}
          >
            <Button variant="secondary">annuler</Button>
            <Button disabled={load} onClick={addOcc} variant="primary">
              ajouter
            </Button>
          </Stack>
        </Form>
      </div>
    </>
  );
};

export default FormOccup;

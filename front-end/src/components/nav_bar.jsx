import React from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";

const MyNavBar = () => {
  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">Gestion salle de classe</Navbar.Brand>
      </Container>
    </Navbar>
  );
};

export default MyNavBar;

SET @clinicId1 = '2c2f831c-9c83-4596-9082-06f250c0b7a8';
INSERT INTO CLINIC(ID, CODE, EMAIL, NAME, PASSWORD) VALUES(@clinicId1, 'B3C0L0', 'admin@petz.com', 'Petz', '12345678');

SET @customerId1 = RANDOM_UUID();
SET @customerId2 = RANDOM_UUID();
SET @customerId3 = RANDOM_UUID();
SET @customerId4 = RANDOM_UUID();
SET @customerId5 = RANDOM_UUID();
INSERT INTO CUSTOMER(ID, CLINIC_ID, NAME, EMAIL, ADDRESS, PHONE) VALUES
(@customerId1, @clinicId1, 'Manoel Siqueira', 'manoelsiqueira@example.com', 'Rua Rubens Lara, 32 - Santos/SP', '139XXXX6177'),
(@customerId2, @clinicId1, 'Laura Alves', 'l.alves@example.com', 'Rua Aristóteles Ferreira, 64 - Santos/SP', '139XXXX4787'),
(@customerId3, @clinicId1, 'José Damasceno', 'jdamasceno3@example.com', 'Rua Escolástica Rosa, 4 - São Vicente/SP', '139XXXX4617'),
(@customerId4, @clinicId1, 'Mariella Campos', 'maricampos@example.com', 'Rua Branco Fatec da Silva, 50 - Santos/SP', '139XXXX6194'),
(@customerId5, @clinicId1, 'João Mendes', 'joaomendes10@example.com', 'Rua A, 52 - Santos/SP', '139XXXX4164');

SET @petId1 = RANDOM_UUID();
SET @petId2 = RANDOM_UUID();
SET @petId3 = RANDOM_UUID();
SET @petId4 = RANDOM_UUID();
SET @petId5 = RANDOM_UUID();
INSERT INTO PET(ID, OWNER_ID, NAME, SPECIES, BREED, SEX, NEUTERED) VALUES
(@petId1, @customerId1, 'Rex', 'Dog', 'Labrador', 'MALE', true),
(@petId2, @customerId2, 'Mel', 'Cat', '', 'FEMALE', true),
(@petId3, @customerId3, 'Tonkay', 'Cat', '', 'MALE', false),
(@petId4, @customerId4, 'José', 'Parrot', '', 'MALE', false),
(@petId5, @customerId4, 'Branco', 'Cat', 'Munchkin', 'MALE', true);

SET @vetId1 = RANDOM_UUID();
SET @vetId2 = RANDOM_UUID();
INSERT INTO VETERINARY(ID, CLINIC_ID, NAME, EMAIL, ADDRESS, PHONE) VALUES
(@vetId1, @clinicId1, 'Doctor Rey', 'drrey@petz.com.br', 'Rua João Caetano, 112 - Santos/SP', '139XXXX6868'),
(@vetId2, @clinicId1, 'Marco dos Anjos', 'mdosanjos@petz.com.br', 'Rua Teixeira de Freitas, 215 - Santos/SP', '139XXXX7776');

INSERT INTO APPOINTMENT(DATE, CLINIC_ID, PET_ID, VETERINARY_ID) VALUES
('2022-05-21 16:30:00', @clinicId1, @petId1, @vetId1),
('2021-06-15 15:00:00', @clinicId1, @petId2, @vetId2);
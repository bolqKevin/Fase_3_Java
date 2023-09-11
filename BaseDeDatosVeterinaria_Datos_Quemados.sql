USE VeterinaryManagement;
GO

-- Agregar datos a la tabla TAGS
INSERT INTO TAGS (TAGNAME)
VALUES
    ('Tag1'),
    ('Tag2'),
    ('Tag3');
GO
-- Agregar datos a la tabla PRODUCTS
INSERT INTO PRODUCTS (PRODUCTNAME, DESCRIPTION_, PRICE, INITIALSTOCK, CURRENTSTOCK, REORDERQUANTITY)
VALUES
    ('Alimento perro', 'Bolsa de 2kg de alimento para perro adulto', 7500.00, 50, 5, 50),
    ('Alimento Gato', 'Bolsa de 500g de alimento para Gatos ', 2500.00, 40, 15, 40),
	('Shampoo para mascotas', 'Shampoo para mascotas olor a vainilla', 3000.00, 50, 4, 50),
	('Pastilla anti pulgas', 'Pastilla anti pulgas para perro adulto', 10000.00, 150, 78, 100),
	('Correa Perro', 'Correa para perros de tama?o mediano', 3000.00, 15, 5, 15),
	('Alimento Cachorro', 'Bolsa de 500g de Alimento para perro cachorro', 5000.00, 30, 0, 30),
	('Alimento Aves', 'Bolsa de 500g de de Alimento para muchos tipos de Aves', 3000.00, 20, 5, 10),
	('Alimento Roedores', 'Bolsa de 250g de Alimento para muchos tipos de roedores', 900.00, 100, 20, 70),
    ('Cepillo Perro', 'Alimento para Perros de alta caliadad', 25000.00, 7, 3, 5);
GO

INSERT INTO PRODUCT_TAGS (PRODUCTID, TAGID)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3),
    (2, 1),
    (3, 3),
    (3, 1),
    (3, 2);
-- Agregar datos a la tabla CLIENTS
INSERT INTO CLIENTS (CLIENTID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, EMAIL, NOTES, TO_BE_DELETED)
VALUES
    ('208340067', 'John', 'Doe', '123 Main St', '123-456-7890', 'john@example.com', 'Notes for John', 0),
    ('123456789', 'Jane', 'Smith', '456 Elm St', '987-654-3210', 'jane@example.com', 'Notes for Jane', 0),
    ('20987654', 'Michael', 'Johnson', '789 Oak St', '555-123-4567', 'michael@example.com', 'Notes for Michael', 0);
go
-- Agregar datos a la tabla EMPLOYEES
INSERT INTO EMPLOYEES (EMPLOYEEID, FIRSTNAME, LASTNAME, CONTACTNUMBER, EMAIL, BIRTHDATE, STARTDATE, SALESHISTORY)
VALUES
    ('109345678', 'David', 'Williams', '555-987-6543', 'david@example.com', '1990-05-15', '2020-01-15', 10000.00),
    ('398263829', 'Sarah', 'Miller', '777-222-3333', 'sarah@example.com', '1985-08-22', '2019-11-01', 7500.00),
    ('493748593', 'Emily', 'Brown', '888-444-5555', 'emily@example.com', '1995-02-10', '2022-03-10', 12000.00);
go

-- Agregar datos a la tabla SUPPLIERS
INSERT INTO SUPPLIERS (FIRSTNAME, LASTNAME, CONTACTNAME, CONTACTNUMBER, EMAIL, ADDRESS_)
VALUES
    ('Supplier1', 'LastName1', 'Contact1', '111-222-3333', 'supplier1@example.com', '789 Supplier St'),
    ('Supplier2', 'LastName2', 'Contact2', '444-555-6666', 'supplier2@example.com', '456 Supplier St'),
    ('Supplier3', 'LastName3', 'Contact3', '777-888-9999', 'supplier3@example.com', '123 Supplier St');
GO
-- Agregar datos a la tabla APPOINTMENTS
INSERT INTO APPOINTMENTS (CLIENTID, APPOINTMENTDATE, SERVICETYPE, EMPLOYEEID, NOTES)
VALUES
    ('208340067', '20230815', 'Grooming', '109345678', 'Notes for appointment 1'),
    ('123456789', '20230820', 'Checkup', '398263829', 'Notes for appointment 2'),
    ('20987654', '20230825', 'Vaccination', '493748593', 'Notes for appointment 3');
go
-- Agregar datos a la tabla PETS
INSERT INTO PETS (PET_NAME, SPECIES, BREED, BIRTHDATE, GENDER, CLIENTID)
VALUES
    ('Buddy', 'Perro', 'Golden Retriever', '2018-04-10', 'Macho', '208340067'),
    ('Whiskers', 'Gato', 'Siamese', '2020-02-20', 'Hembra', '123456789'),
    ('Rocky', 'Perro', 'Poodle', '2019-09-05', 'macho', '20987654');

-- Agregar datos a la tabla VACCINATION_RECORD
INSERT INTO VACCINATION_RECORD (PETID, VACCINATIONDATE, VACCINENAME, VETERINARIAN, NOTES)
VALUES
    (1, '2023-07-15', 'Rabies', 'Dr. Smith', 'Notes for vaccination 1'),
    (2, '2023-06-20', 'Feline Distemper', 'Dr. Johnson', 'Notes for vaccination 2'),
    (3, '2023-08-05', 'Canine Parvovirus', 'Dr. Brown', 'Notes for vaccination 3');

-- Agregar datos a la tabla MEDICAL_RECORD
INSERT INTO MEDICAL_RECORD (PETID, RECORDDATE, VETERINARIAN, DIAGNOSIS, TREATMENT)
VALUES
    (1, '20230801', 'Dr. Miller', 'Ear infection', 'Prescribed antibiotics'),
    (2, '20230810', 'Dr. Williams', 'Dental issues', 'Dental cleaning and extractions'),
    (3, '20230818', 'Dr. Davis', 'Lameness', 'X-rays and pain management');
go
-- Agregar datos a la tabla SALES
INSERT INTO SALES (SALEDATE, CLIENTID, EMPLOYEEID, DISCOUNT)
VALUES
    ('2023-08-02', '208340067', '109345678', '0%'),
    ('2023-08-08', '123456789', '398263829', '0%'),
    ('2023-08-12', '20987654',  '493748593', '0%');

-- Agregar datos a la tabla SALE_DETAIL
INSERT INTO SALE_DETAIL (SALEID, PRODUCTID, QUANTITY, UNITPRICE)
VALUES
    (1, 1, 5, 48.00),
    (2, 2, 3, 25.00),
    (3, 3, 10, 20.00);
go

-- Agregar datos a la tabla PURCHASES
INSERT INTO PURCHASES (PURCHASEDATE, SUPPLIERID)
VALUES
    ('2023-08-01', 1),
    ('2023-08-05', 2),
    ('2023-08-10', 3);
go
-- Agregar datos a la tabla PURCHASE_DETAILS
INSERT INTO PURCHASE_DETAILS (PURCHASEID, PRODUCTID, QUANTITY, UNITPRICE)
VALUES
    (1, 1, 50, 45.00),
    (2, 2, 75, 28.00),
    (3, 3, 100, 22.00);


CREATE TABLE PUBLIC.test_1 (
    dummy VARCHAR(80) NOT NULL
);

CREATE TABLE PUBLIC.test_2 (
    dummy VARCHAR(80) NOT NULL
);

CREATE TABLE PUBLIC.test_3 (
    id_agree INT NOT NULL,
    id_bill INT NOT NULL,
    CONSTRAINT PK_TEST_3 PRIMARY KEY (id_agree, id_bill),
    bill_no VARCHAR(80) NOT NULL,
    bill_sum DECIMAL NOT NULL
);
CREATE TABLE Conta (
    id INT PRIMARY KEY IDENTITY(1,1),
    dataVencimento DATE,
    dataPagamento DATE,
    valor DECIMAL(18,2),
    descricao NVARCHAR(255),
    situacao NVARCHAR(255)
);
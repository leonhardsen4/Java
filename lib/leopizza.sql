# criação da database
CREATE DATABASE leopizza;

# seleção da database 
use leopizza;

# criação da tabela funcionario
CREATE TABLE Funcionario (
    cpf varchar(11) PRIMARY KEY,
    nome varchar(100) not null,
    senha varchar(128) not null,
    cargo tinyint not null
);

# criação da tabela cliente
CREATE TABLE Cliente (
    id int auto_increment PRIMARY KEY,
    nome varchar(100) not null,
    endereco text not null,
    telefone varchar(11) not null UNIQUE,
    email varchar(100),
    cep varchar(8) not null,
    pontos int default 0
);

# criação da tabela produto
CREATE TABLE Produto (
    id int auto_increment PRIMARY KEY,
    nome varchar(255) not null,
    tipo tinyint not null,
    descricao text,
    preco decimal(6,2) not null,
    promocao boolean,
    imagem mediumblob
);

#criação da tabela pedido
CREATE TABLE Pedido (
    numero int auto_increment PRIMARY KEY,
    `data` datetime default now(),
    cliente_id int not null,
    end_entrega varchar(500) not null,
    retirada boolean not null,
    tx_entrega decimal(6,2) default 0,
    troco decimal(6,2) default 0,
    vtotal decimal(8,2) not null,
    observacao varchar(255),
    forma_pgto tinyint not null,
    func_cpf varchar(11) not null
);

 # adiciona FK para Cliente
ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_Cliente
    FOREIGN KEY (cliente_id)
    REFERENCES Cliente (id);
    
 # adiciona FK para Funcionario
ALTER TABLE Pedido ADD CONSTRAINT FK_Pedido_Funcionario
    FOREIGN KEY (func_cpf)
    REFERENCES Funcionario (cpf);
    
# criação da tabela itempedido
CREATE TABLE ItemPedido (
    id int auto_increment PRIMARY KEY,
    produto_id int not null,
    qtd smallint not null,
    total decimal(6,2) not null,
    observacao varchar(100),
    pedido_numero int not null
);

 # adiciona FK para Produto
ALTER TABLE ItemPedido ADD CONSTRAINT FK_ItemPedido_Produto
    FOREIGN KEY (produto_id)
    REFERENCES Produto (id);
    
 # adiciona FK para Pedido
ALTER TABLE ItemPedido ADD CONSTRAINT FK_ItemPedido_Pedido
    FOREIGN KEY (pedido_numero)
    REFERENCES Pedido (numero)
    ON DELETE CASCADE;

 # Cria a view
create view view_pedidos
as 
select p.numero, p.data, c.nome "nome_cliente", c.telefone, p.end_entrega, p.retirada, 
p.tx_entrega, p.troco, p.vtotal, p.observacao, p.forma_pgto, f.nome "nome_func", 
i.id, i.qtd, pr.nome "Produto", i.total, i.observacao "obs_item"
from pedido p
inner join cliente c
on p.cliente_id = c.id
inner join funcionario f
on p.func_cpf = f.cpf
inner join itempedido i
on i.pedido_numero = p.numero
inner join produto pr
on i.produto_id = pr.id
;
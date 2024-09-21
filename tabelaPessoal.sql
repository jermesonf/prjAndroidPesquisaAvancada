CREATE database exemplo
GO
USE Exemplo
GO
CREATE TABLE [dbo].[pessoal](
	[id_pessoal] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](30) NULL,
	[endereco] [varchar](30) NULL,
	[telefone] [varchar](20) NULL,
 CONSTRAINT [PK_pessoal] PRIMARY KEY CLUSTERED 
(
	[id_pessoal] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
insert into pessoal (nome, endereco, telefone) values ('maria do Carmo', 'Rua da Pedra, 98','2335-1724')
insert into pessoal (nome, endereco, telefone) values ('Paulo Pedro Pedra', 'Av. Flinkstone, 1','98124-3015')
insert into pessoal (nome, endereco, telefone) values ('Ana Conda Ferreira', 'Rua da Serpente, 2','96660-0666')
insert into pessoal (nome, endereco, telefone) values ('Isaac Newton', 'Alameda do pensador, 7','2338-1728')
insert into pessoal (nome, endereco, telefone) values ('Ana Maria Trindade', 'Rua sem número, 4','99999-8888')
insert into pessoal (nome, endereco, telefone) values ('Pedro Vas Concelo', 'Rua sem saída, 0','91234-5671')
insert into pessoal (nome, endereco, telefone) values ('Aarão', 'Rua do Gênises, 129','0000-0001')

select * from pessoal

Select * from pessoal WHERE nome like 'a%' 
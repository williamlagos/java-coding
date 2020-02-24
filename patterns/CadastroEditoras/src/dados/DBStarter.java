package dados;

import java.sql.*;

public class DBStarter {
    
    public static void inicializar() throws Exception {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
    }
    
    public static void criarBd() throws Exception{
    	String sqlAutores,sqlEditoras,sqlLivros,sqlLivroAutores,sqlVendas,sqlItensVenda;
        Connection con = DriverManager.getConnection("jdbc:derby:cadastro;create=true");
        Statement sta = con.createStatement();
        sqlAutores = "CREATE TABLE AUTORES("
        +"CODIGO int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
        +"PRIMEIRONOME varchar(100) NOT NULL,"
        +"ULTIMONOME varchar(100) NOT NULL,"
        +"CONSTRAINT PK_AUTORES PRIMARY KEY (CODIGO))";
        sqlEditoras = "CREATE TABLE EDITORAS("
        +"CODIGO int"
        +"NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
        +"NOME varchar(100) NOT NULL,"
        +"CONSTRAINT PK_EDITORAS PRIMARY KEY (CODIGO)";
        sqlLivros = "CREATE TABLE LIVROS("
        +"CODIGO int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
        +"TITULO varchar(100) NOT NULL,"
        +"ANO int NOT NULL,"
        +"CODEDITORA int NOT NULL,"
        +"CONSTRAINT PK_LIVROS PRIMARY KEY (CODIGO),"
        +"CONSTRAINT FK_LIVROS_EDITORAS FOREIGN KEY (CODEDITORA) REFERENCES EDITORAS(CODIGO)";
        sqlLivroAutores = "CREATE TABLE LIVROSAUTORES("
        +"CODLIVRO int NOT NULL,"
        +"CODAUTOR int NOT NULL,"
        +"CONSTRAINT PK_LIVROSAUTORES PRIMARY KEY (CODLIVRO,CODAUTOR),"
        +"CONSTRAINT FK_LIVROSAUTORES_LIVROS FOREIGN KEY (CODLIVRO) REFERENCES LIVROS(CODIGO),"
        +"CONSTRAINT FK_LIVROSAUTORES_AUTORES FOREIGN KEY (CODAUTOR) REFERENCES AUTORES(CODIGO))";
        sqlVendas = "CREATE TABLE VENDAS("
        +"CODIGO int NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),"
        +"NOMECLIENTE varchar(200) NOT NULL,"
        +"CPFCLIENTE char(11),"
        +"CNPJCLIENTE char(14),"
       	+"DATA date NOT NULL,"
        +"CONSTRAINT PK_VENDAS PRIMARY KEY (CODIGO))";
        sqlItensVenda = "CREATE TABLE ITENSVENDA("
        +"CODVENDA int NOT NULL,"
        +"CODLIVRO int NOT NULL,"
        +"QUANTIDADE int NOT NULL,"
        +"CONSTRAINT PK_ITENSVENDA PRIMARY KEY (CODVENDA,CODLIVRO),"
        +"CONSTRAINT FK_ITENSVENDA_LIVROS FOREIGN KEY (CODLIVRO) REFERENCES LIVROS(CODIGO),"
        +"CONSTRAINT FK_ITENSVENDA_VENDAS FOREIGN KEY (CODVENDA) REFERENCES VENDAS(CODIGO)";
        sta.executeUpdate(sqlAutores);
        sta.executeUpdate(sqlEditoras);
        sta.executeUpdate(sqlLivros);
        sta.executeUpdate(sqlLivroAutores);
        sta.executeUpdate(sqlVendas);
        sta.executeUpdate(sqlItensVenda);
        sta.close();
        con.close();
    }
    
    public static Connection conectarBd() throws Exception{
        return DriverManager.getConnection("jdbc:derby:cadastro");
    }
}


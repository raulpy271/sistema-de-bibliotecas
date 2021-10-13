
# comandos ao estar logado em bibliotecário

```
ADICIONAR_LIVRO <isbn> <ano_publicacao> <paginas> <num_estante> <autor> <titulo> <categoria_1> ... <categoria_n>  

EDIT_LIVRO <isbn> <ano_publicacao> <paginas> <num_estante> <autor> <titulo> <categoria_1> ... <categoria_n>  

ADD_ITEM <isnb> <quantidade>

REMOVE_ITEM <item_id>

ADD_USER <tipo:membro|bibliotecario> <nome> <endereço> <senha>

REMOVE_USER <user_id>

WHO_DO_CHECKOUT <item_id>

ITENS_OF_THIS_USER <user_id>


```

# comandos ao estar logado em qualquer conta

```

LOGIN <nome> <senha>

PESQUISA <parametro:autor|titulo|assunto|ano> <valor_do_parametro>

PESQUISA_POR_TODOS_PARAMETROS <valor_do_autor> <valor_do_titulo> <valor_do_assunto> <valor_do_ano>

CHECKOUT <isbn> 

DEVOLVER <item_id>

RENOVAR <item_id>

RESERVAR <item_id>
```


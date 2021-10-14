package biblioteca;

import java.util.Arrays;
import java.lang.NumberFormatException;

import biblioteca.domain.*;
import biblioteca.Permission;
import biblioteca.Biblioteca;
import biblioteca.Commands;
import biblioteca.utils.Utils;

public class Controller {
    private Biblioteca biblioteca;
    private Permission permission;

    public Controller(Biblioteca biblioteca, Permission permission) {
        this.biblioteca = biblioteca;
        this.permission = permission;
    }

    public String loggedInfo(String[] argumentos) {
        return View.user(this.permission.getCurrentUser());
    }

    public String addLivro(String[] argumentos) {
        int argumentos_necessarios = 7;
        if (argumentos.length >= argumentos_necessarios) {
            String isbn = argumentos[0];
            int ano_publicacao = Integer.parseInt(argumentos[1]);
            int num_paginas = Integer.parseInt(argumentos[2]);
            int num_estante = Integer.parseInt(argumentos[3]);
            String autor = argumentos[4];
            String titulo = argumentos[5];
            String[] categorias = Arrays.copyOfRange(argumentos, 6, argumentos.length);
            this.biblioteca.createLivro(
                isbn, ano_publicacao, num_paginas, num_estante, autor, titulo, categorias
            );
            return "Livro inserido com sucesso!";
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String addItem(String[] argumentos) {
        int argumentos_necessarios = 2;
        if (argumentos.length >= argumentos_necessarios) {
            String isbn = argumentos[0];
            int quantidade_itens = Integer.parseInt(argumentos[1]);
            if (biblioteca.getLivro(isbn) != null) {
                this.biblioteca.createItens(isbn, quantidade_itens);
                return "Itens inseridos com sucesso!";
            } else {
                return "Não existe livros com o isbn digitado";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String editLivro(String[] argumentos) {
        int argumentos_necessarios = 7;
        if (argumentos.length >= argumentos_necessarios) {
            String isbn = argumentos[0];
            int ano_publicacao = Integer.parseInt(argumentos[1]);
            int num_paginas = Integer.parseInt(argumentos[2]);
            int num_estante = Integer.parseInt(argumentos[3]);
            String autor = argumentos[4];
            String titulo = argumentos[5];
            String[] categorias = Arrays.copyOfRange(argumentos, 6, argumentos.length);
            this.biblioteca.editLivro(
                isbn, ano_publicacao, num_paginas, num_estante, autor, titulo, categorias
            );
            return "Livro editado com sucesso!";
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String search(String[] argumentos) {
        int argumentos_necessarios = 2;
        if (argumentos.length == argumentos_necessarios) {
            String tipo = argumentos[0];
            String valor_para_pesquisar = argumentos[1];
            Livro[] books;
            switch (tipo) {
                case "assunto":
                    books = biblioteca.searchLivro("", valor_para_pesquisar, "", 0);
                    return View.books(books);
                case "titulo":
                    books = biblioteca.searchLivro(valor_para_pesquisar, "", "", 0);
                    return View.books(books);
                case "autor":
                    books = biblioteca.searchLivro("", "", valor_para_pesquisar, 0);
                    return View.books(books);
                case "ano": 
                    try {
                        int ano = Integer.parseInt(valor_para_pesquisar);
                        books = biblioteca.searchLivro("", "", "", ano);
                        return View.books(books);
                    } catch (NumberFormatException e) {
                        return "Não foi possivel fazer parser do ano";
                    }
                default:
                    return "Este tipo de pesquisa não é suportado";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String addUser(String[] argumentos) {
        int argumentos_necessarios = 4;
        if (argumentos.length == argumentos_necessarios) {
            String tipo = argumentos[0];
            String nome = argumentos[1];
            String endereco = argumentos[2];
            String senha = argumentos[3];
            int user_id = -1;
            if (tipo.equals("membro")) {
                user_id = biblioteca.createConta(nome, endereco, senha, false);
            } else if (tipo.equals("bibliotecario")) {
                user_id = biblioteca.createConta(nome, endereco, senha, true);
            } else {
                return "tipo de usuário não reconhecido";
            }
            return "Usuário adicionado. user_id: " + user_id;
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String removeUser(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            try {
                int user_id = Integer.parseInt(argumentos[0]);
                if (user_id != permission.getCurrentUser().getID()) {
                    biblioteca.removeConta(user_id);
                    return "Conta removida com sucesso!";
                } else {
                    return "Antes de deletar a conta atual, faça login em outra conta";
                }

            } catch (NumberFormatException e) {
                return "Não foi possivel fazer parser do id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String removeItem(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                Item item = biblioteca.getItem(item_id);
                if (item != null) {
                    if (item.getStatus() != Status.StatusEnum.DISPONIVEL) {
                        return "Item não disponivel. Está emprestado ou reservado";
                    } else {
                        biblioteca.removeItem(item_id);
                        return "Item removido com sucesso!";
                    }
                } else {
                    return "Não há itens com esse id";
                }
            } catch(NumberFormatException e) {
                return "Não foi possivel fazer parser do id";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }

    }

    public String checkout(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            String isbn = argumentos[0];
            if (biblioteca.getLivro(isbn) != null) {
                int conta_id = permission.getCurrentUser().getID();
                try {
                    int item_id = biblioteca.getItemDisponivel(isbn, conta_id);
                    this.biblioteca.checkoutLivro(item_id, conta_id);
                    return "Feito checkout de item_id " + item_id;
                } catch (Exception e) {
                    return e.getMessage();
                }
            } else {
                return "Não existe livros com o isbn digitado";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String devolver(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            int conta_id = permission.getCurrentUser().getID();
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                biblioteca.devolverLivro(item_id, conta_id);
                return "Devolução com sucesso";
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String renovar(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            int conta_id = permission.getCurrentUser().getID();
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                biblioteca.renovarLivro(item_id, conta_id);
                return "Renovação com sucesso";
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String reservar(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            int conta_id = permission.getCurrentUser().getID();
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                biblioteca.reservarLivro(item_id, conta_id);
                return "Reserva feita com sucesso";
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String who_do_checkout(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                int conta_id = biblioteca.searchContaQFezCheckout(item_id);
                Conta conta = biblioteca.getConta(conta_id);
                return View.user(conta);
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String itensOfThisUser(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            try {
                int conta_id = Integer.parseInt(argumentos[0]);
                Conta conta = biblioteca.getConta(conta_id);
                if (conta != null) {
                    Emprestimo[] emprestimos = conta.getAllEmprestimos();
                    return View.emprestimos(emprestimos, biblioteca);
                } else {
                    return "Não foi encontrado conta com esse ID";
                }
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    public String checkAtraso(String[] argumentos) {
        int argumentos_necessarios = 1;
        if (argumentos.length == argumentos_necessarios) {
            try {
                int item_id = Integer.parseInt(argumentos[0]);
                return View.contaAtraso(biblioteca.getContaComAtraso(item_id));
            } catch (NumberFormatException e) {
                return "Erro ao fazer parser do item_id";
            } catch (RuntimeException e) {
                return e.getMessage();
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    private String login(String[] argumentos) {
        int argumentos_necessarios = 2;
        if (argumentos.length == argumentos_necessarios) {
            String nome = argumentos[0];
            String senha = argumentos[1];
            Conta conta = biblioteca.getContaPorNome(nome);
            if (conta == null) {
                return "Conta não existe";
            }
            if (conta.getSenha().equals(senha)) {
                permission.setCurrentUser(conta);
                return "login com sucesso";
            } else {
                return "Senha inválida";
            }
        } else {
            return "Não foram adicionados argumentos suficientes";
        }
    }

    private String help(String[] argumentos) {
        String helpFile = "commands.md";
        String result = Utils.StringFromFile(helpFile);
        if (result != null) {
            return result;
        } else {
            return "Não foi possivel encontrar o arquivo de ajuda";
        }
    }

    public String lidaComInputDoUsuario(String input) {
        String[] comando_e_argumentos = Utils.divideArgumentos(input);
        String comando = comando_e_argumentos[0];
        if (!permission.canAcess(comando)) {
            return "Usuário não tem permissão de executar esse comando.";
        }
        String[] argumentos = 
            Arrays.copyOfRange(comando_e_argumentos, 1, Utils.countNotNull(comando_e_argumentos));
        switch (comando) {
            case Commands.LOGIN:
                return this.login(argumentos);
            case Commands.SEARCH:
                return this.search(argumentos);
            case Commands.HELP:
                return this.help(argumentos);
            case Commands.ADD_LIVRO:
                return this.addLivro(argumentos);
            case Commands.ADD_ITEM:
                return this.addItem(argumentos);
            case Commands.EDIT_LIVRO:
                return this.editLivro(argumentos);
            case Commands.ADD_USER:
                return this.addUser(argumentos);
            case Commands.REMOVE_USER:
                return this.removeUser(argumentos);
            case Commands.REMOVE_ITEM:
                return this.removeItem(argumentos);
            case Commands.LOGGED_INFO:
                return this.loggedInfo(argumentos);
            case Commands.CHECKOUT:
                return this.checkout(argumentos);
            case Commands.DEVOLVER:
                return this.devolver(argumentos);
            case Commands.RENOVAR:
                return this.renovar(argumentos);
            case Commands.RESERVAR:
                return this.reservar(argumentos);
            case Commands.WHO_DO_CHECKOUT:
                return this.who_do_checkout(argumentos);
            case Commands.ITENS_OF_THIS_USER:
                return this.itensOfThisUser(argumentos);
            case Commands.CHECK_ATRASO:
                return this.checkAtraso(argumentos);
            default:
                return "Comando não disponível. Digite HELP para ajuda.";
        }
    }
}


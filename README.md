# Projeto P2 
Projeto de Laboratorio de Programação 2
# Links Importantes
- Casos de teste : < https://drive.google.com/drive/folders/1H7au_Jzxi8UKih0bW8jVqAfe00ROOOMJ > 
- Trello do projeto: < https://trello.com/b/kCepE5RE/projeto-prog2 >
# Caso de Uso 1. Pesquisas

A pesquisa é atividade base para a estruturação e criação de um novo conhecimento. A pesquisa científica é aquela que segue o método científico para a construção de um novo entendimento, compreensão ou processo.

Existem diferentes maneiras para estruturar uma pesquisa. A pesquisa aplicada[1] se consolida como um dos diferentes tipos de pesquisa de propósito prático e específico buscando, principalmente, resolver problemas concretos. Toda pesquisa aplicada se estrutura um campo de interesse e uma metodologia a ser seguida.

Uma pesquisa, ao ser cadastrada, é considerada como ativa. A pesquisa e todas as entidades associadas podem ser alteradas e editadas enquanto a pesquisa estiver ativa. Uma vez encerrada a pesquisa, não é permitida a alteração em nenhuma de suas entidades.

- Para o cadastro de uma pesquisa é preciso:

    Descrição Um texto livre com um resumo da pesquisa a ser realizada.
    Campo de interesse Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255 caracteres.

Após cadastrada, a pesquisa irá ter um código associado. Este código é gerado automaticamente pelas primeiras três letras do campo de interesse mais um valor inteiro começando em 1. Exemplo: na pesquisa sobre homofobia em mensagens online de alunos de computação do primeiro período, com o campo de interesse: "computação, homofobia", o código gerado será COM1. Uma nova pesquisa cadastrada no campo de interesse "computação, interação usuário" terá o código COM2.

Ao exibir uma pesquisa deve ser retornada a string:

"CÓDIGO - Descrição - Campo de interesse"

- Regras de validação:

    Descrição não pode ser vazia ou em branco

    Mensagem: "Descricao nao pode ser nula ou vazia."

    Campo de interesse não pode ser vazio, ter tópicos em branco (ex.: "computação, ,teste"), ou tópicos com menos de 3 caracteres.

    Mensagem: "Formato do campo de interesse invalido."

    Só deve ser possível alterar, encerrar, ativar e exibir pesquisas já cadastradas.

    Mensagem: "Pesquisa nao encontrada."

    Não deve permitir desativar uma pesquisa já desativada

    Mensagem: "Pesquisa desativada."

    Não deve permitir ativar uma pesquisa já ativada

    Mensagem: "Pesquisa ja ativada."

- Métodos da facade:

    String cadastraPesquisa(String descricao, String campoDeInteresse)

    deve retornar o código gerado da pesquisa

    void alteraPesquisa(String código, String conteúdoASerAlterado, String novoConteúdo)

    conteúdoASerAlterado pode ser "DESCRICAO" ou "CAMPO"

    void encerraPesquisa(String codigo, String motivo)
    void ativaPesquisa(String codigo)
    String exibePesquisa(String codigo)
    boolean ehAtiva(String codigo)
# Caso de Uso 2. Pesquisadores

Todos nós somos pesquisadores. Cada pessoa aplica de alguma estrutura ou método para abordar e resolver seus problemas. Quando o problema toma uma relevância maior e quando desenvolvemos um método estruturado em que desenvolvemos um conhecimento aplicável a outras instâncias do mesmo problema, estamos realizando uma pesquisa científica.
No nosso sistema, estaremos cadastrando pesquisadores que podem ter 3 diferentes funções: Estudante, Professor e Externo. O pesquisador externo é caracterizado por não ser nem aluno nem professor. Além da FUNÇÃO, todo pesquisador apresenta um NOME, BIOGRAFIA, EMAIL e FOTO. Todo pesquisador é identificado unicamente por seu email.

Cada pesquisador tem uma pequena biografia que é uma descrição textual de sua atuação em pesquisa. Além disso, o pesquisador têm um contato e um link para uma foto.

A validação de email é complexa, mas, para o nosso sistema, vamos considerar que todo email têm que ter pelo menos uma letra ou um número antes e depois de uma arroba. Para a foto no perfil, considere que o usuário irá colocar uma URL começando com http ou https seguido de :// e um endereço.

- O pesquisador têm como representação textual:

        NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO

- Regras de validação:

    Nome, biografia, função, email e foto não podem ser vazios ou em branco

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio." (ex.: "Campo NOME nao pode ser nulo ou vazio.")

    O email deve seguir o formato especificado

    Mensagem: "Formato de email invalido."

    O endereço da foto deve começar com http:// ou https://

    Mensagem: "Formato de foto invalido."

    Não deve ser possível alterar, ativar, desativar ou exibir um pesquisador não cadastrado (email não encontrado)

    Mensagem: "Pesquisador nao encontrado."

    Não deve ser possível alterar, desativar ou exibir um pesquisador inativo

    Mensagem: "Pesquisador inativo."

    Não deve ser possível ativar um pesquisador já ativo, nem desativar um pesquisador já inativo.

    Mensagem: "Pesquisador ja ativado."
    Mensagem: "Pesquisador inativo."

- Métodos da facade:

    void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL)
    void alteraPesquisador(String email, String atributo, String novoValor)

    atributo pode ser NOME, FUNCAO, BIOGRAFIA, EMAIL e FOTO

    void desativaPesquisador(String email)
    void ativaPesquisador(String email)
    String exibePesquisador(String email)
    
    # Caso de Uso 3. Problemas e Objetivos
    Um problema deve ser, primeiro, relevante. O problema a ser abordado na pesquisa deve ser fonte de incômodo a algo ou alguém. Ao mesmo tempo, para que seja um problema objeto de pesquisa, é necessário que seja também viável.
    A homofobia é problemas existente e grave em nosso mundo, mas é inviável resolver esse problemas inteiro de uma vez. Uma pesquisa que trabalhe com o problema do discurso homofóbico em chats online de alunos de computação de primeiro período é algo bem mais factível de ser entendido e resolvido.

Dizemos que um bom problema deve[2]:

    Abordar condições ou aspectos que apresentam fatos observáveis. i.e., devemos poder ver e caracterizar o problema acontecendo (ex.: quantidade de mensagens homofóbicas visualizadas por mês)
    Ser definido e específico, mas significativamente ligado a uma área maior e geral (ex.: o problema apresentado é específico, mas faz parte do combate a homofobia como um todo)
    Ser passível ao tratamento experimental, de forma que seja possível identificar a validade de uma possível solução ao problema (ex.: eu tenho a possibilidade e permissão institucional para fazer uma roda de conversa com alunos que escrevem mensagens homofóbicas e alunos homoafetivos e observar o efeito)
    Despertar a curiosidade e interesse do pesquisador
    Ser relevante, trabalhar algo que não está sendo já avaliado ou que já foi resolvido de uma maneira prática e facilmente aplicável.

Na pesquisa aplicada, os objetivos de uma pesquisa costumam trabalhar para a resolução do problema. Os objetivos[3] define a finalidade da pesquisa e comumente são estruturados em um objetivo geral, um objetivo mais abrangente e que responde diretamente ao problema da pesquisa, e em objetivos específicos, que delimitam alvos específicos para atingir o objetivo geral, ou seja, a uma meta bem definida.

No problema apresentado anteriormente, posso ter como objetivos:

    Objetivo geral: Diminuir a frequência de mensagens homofóbicas trocadas em chats online entre alunos de primeiro período de computação
    Objetivo específico 1: Realizar rodas de conversa entre alunos do curso, incluindo especialmente aqueles que emitem mensagens homofóbicas e alunos ou pessoas homoafetivas.
    Objetivo específico 2: Detectar, através de bot eletrônico, a presença de mensagens eletrônicas com conteúdo homofóbico

Observe que o objetivo geral talvez não resolva plenamente o problema apresentado, mas é um passo nessa direção. Os objetivos específicos ajudam e fazem parte do contexto para atingir o objetivo geral. Observe que todos os objetivos são claros, diretos e viáveis. Observe ainda que os objetivos específicos definem metas em que é possível dizer se o objetivo foi atingido ou não.
Nesse contexto, um problema é caracterizados pelos atributos DESCRIÇÃO e VIABILIDADE (inteiro 1 a 5).

Todo objetivo tem um TIPO (GERAL ou ESPECIFICO), uma DESCRIÇÃO, ADERÊNCIA AO PROBLEMA (inteiro de 1 a 5) e VIABILIDADE (inteiro de 1 a 5).

Objetivos são identificados unicamente pelo código O + id gerado automaticamente (a partir de 1). Problemas são identificados pelo código P + id gerado automaticamente (a partir de 1). Ao apagar um problema/objetivo, isso não altera o código a ser gerado para a próxima entidade.

- O formato textual do problema é:

        CÓDIGO - DESCRIÇÃO - VIABILIDADE

- O formato textual do objetivo é:

        CÓDIGO - TIPO - DESCRIÇÃO - VALOR

        (VALOR é a soma de ADERÊNCIA e VIABILIDADE)

- Regras de validação:

    Nenhum campo pode ser vazio.

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio."

    Viabilidade e aderência devem ter valores entre 1 e 5

    Mensagem: "Valor invalido de NOME_DO_CAMPO."

    Não deve ser possível apagar ou exibir objetivos/problemas inexistentes

    Mensagem: "Problema nao encontrado"
    Mensagem: "Objetivo nao encontrado"

- Métodos da facade:

    String cadastraProblema(String descricao, int viabilidade)
    String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade)
    void apagarProblema(String codigo)
    void apagarObjetivo(String codigo)
    String exibeProblema(String codigo)
    String exibeObjetivo(String codigo)
    
    # Caso de Uso 4. Atividades Metodológicas
    Para atingir um objetivo, especialmente os objetivos específicos, é importante descrever e planejar atividades a serem realizadas para obter resultados. Cada atividade planejada apresenta uma descrição do que deve ser feito, uma duração planejada, resultados esperados e um risco associado.

É importante que toda atividade apresente uma duração em dias, semanas ou meses (veja https://docs.oracle.com/javase/8/docs/api/java/time/Period.html#ofDayss-int- como uma sugestão de implementação, ex.: Period.ofDays(8)).

Toda atividade têm um risco associado. O risco pode ser em três níveis: BAIXO, MEDIO, ALTO. Além do nível, há uma descrição do que ocasiona o risco e o que deve ser feito para mitigar o risco.

Por fim, os resultados esperados são compostos de uma lista de itens que podem ser marcados como feitos ou não. Existem apenas dois estados para cada item: REALIZADO e PENDENTE. Toda lista começa, por padrão, com cada item marcado como PENDENTE.

Cada atividade é identificada pelo código A + valor começando a partir de 1, gerado automaticamente pelo sistema. Cada atividade é representada pela string:

DESCRIÇÃO (NIVEL_RISCO - DESC_RISCO):

- REALIZADO - ITEM1

- REALIZADO - ITEM2

- PENDENTE - ITEM3

A ordem dos itens é determinada pela ordem de cadastro, não podem existir itens repetidos e cada item é identificado por sua ordem de cadastro. Também deve ser possível apagar uma atividade. Ao apagar uma atividade isso não altera o código a ser gerado para a próxima entidade.

- Regras de validação:

    Nenhum campo pode ser vazio.

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio."

    Nivel de campo inválido.

    Mensagem: "Valor invalido do nivel do risco."

    Item vazio.

    Mensagem: "Item nao pode ser nulo ou vazio."

    Não deve ser possível apagar/exibir/trabalhar com atividades/itens inexistentes

    Mensagem: "Atividade nao encontrada"
    Mensagem: "Item nao encontrado"

- Métodos da facade:

    String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco)
    void apagaAtividade(String codigo)
    void cadastraItem(String codigo, String item)
    String exibeAtividade(String codigo)
    int contaItensPendentes(String codigo)
    int contaItensRealizados(String codigo)

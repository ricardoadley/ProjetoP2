# Projeto P2 
Projeto de Laboratorio de Programação 2

# Links Importantes
- Casos de teste : < https://drive.google.com/drive/folders/1H7au_Jzxi8UKih0bW8jVqAfe00ROOOMJ > 
- Trello do projeto: < https://trello.com/b/kCepE5RE/projeto-prog2 >

# Alunos responsáveis
- José Matheus do Nascimento Gama 119110770
- Vinícius Moraes Valença Varjão 119110332
- Ricardo Adley da Silva Sena 119110702
- Ana Beatriz da Silva Truta 119110996

# Caso de Uso 1. Pesquisas

A pesquisa é atividade base para a estruturação e criação de um novo conhecimento. A pesquisa científica é aquela que segue o método científico para a construção de um novo entendimento, compreensão ou processo.

Existem diferentes maneiras para estruturar uma pesquisa. A pesquisa aplicada[1] se consolida como um dos diferentes tipos de pesquisa de propósito prático e específico buscando, principalmente, resolver problemas concretos. Toda pesquisa aplicada se estrutura um campo de interesse e uma metodologia a ser seguida.

Uma pesquisa, ao ser cadastrada, é considerada como ativa. A pesquisa e todas as entidades associadas podem ser alteradas e editadas enquanto a pesquisa estiver ativa. Uma vez encerrada a pesquisa, não é permitida a alteração em nenhuma de suas entidades.

Para o cadastro de uma pesquisa é preciso:

    Descrição Um texto livre com um resumo da pesquisa a ser realizada.
    Campo de interesse Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255 caracteres.

Após cadastrada, a pesquisa irá ter um código associado. Este código é gerado automaticamente pelas primeiras três letras do campo de interesse mais um valor inteiro começando em 1. Exemplo: na pesquisa sobre homofobia em mensagens online de alunos de computação do primeiro período, com o campo de interesse: "computação, homofobia", o código gerado será COM1. Uma nova pesquisa cadastrada no campo de interesse "computação, interação usuário" terá o código COM2.

Ao exibir uma pesquisa deve ser retornada a string:

"CÓDIGO - Descrição - Campo de interesse"

Regras de validação:

    Descrição não pode ser vazia ou em branco

    Mensagem: "Descricao nao pode ser nula ou vazia."

    Campo de interesse não pode ser vazio, ter mais que 255 caracteres, ter tópicos em branco (ex.: "computação, ,teste"), ou tópicos com menos de 3 caracteres.

    Mensagem: "Formato do campo de interesse invalido."

    Só deve ser possível alterar, encerrar, ativar e exibir pesquisas já cadastradas.

    Mensagem: "Pesquisa nao encontrada."

    Não deve permitir desativar uma pesquisa já desativada
    Só deve ser possível alterar os campos de “DESCRICAO” e “CAMPO” .
         -         Mensagem: “Nao e possivel alterar esse valor de pesquisa.” 

    Mensagem: "Pesquisa desativada."

    Não deve permitir ativar uma pesquisa já ativada

    Mensagem: "Pesquisa ja ativada."

Métodos da facade:

    String cadastraPesquisa(String descricao, String campoDeInteresse)

    deve retornar o código gerado da pesquisa

    void alteraPesquisa(String código, String conteúdoASerAlterado, String novoConteúdo)

    conteúdoASerAlterado pode ser "DESCRICAO" ou "CAMPO"

    void encerraPesquisa(String codigo, String motivo)
    void ativaPesquisa(String codigo)
    String exibePesquisa(String codigo)
    boolean pesquisaEhAtiva(String codigo)

# Caso de Uso 2. Pesquisadores

Todos nós somos pesquisadores. Cada pessoa aplica de alguma estrutura ou método para abordar e resolver seus problemas. Quando o problema toma uma relevância maior e quando desenvolvemos um método estruturado em que desenvolvemos um conhecimento aplicável a outras instâncias do mesmo problema, estamos realizando uma pesquisa científica.

No nosso sistema, estaremos cadastrando pesquisadores que podem ter 3 diferentes funções: Estudante, Professor e Externo. O pesquisador externo é caracterizado por não ser nem aluno nem professor. Além da FUNÇÃO, todo pesquisador apresenta um NOME, BIOGRAFIA, EMAIL e FOTO. Todo pesquisador é identificado unicamente por seu email.

Cada pesquisador tem uma pequena biografia que é uma descrição textual de sua atuação em pesquisa. Além disso, o pesquisador têm um contato e um link para uma foto.

A validação de email é complexa, mas, para o nosso sistema, vamos considerar que todo email têm que ter pelo menos uma letra ou um número antes e depois de uma arroba. Para a foto no perfil, considere que o usuário irá colocar uma URL começando com http ou https seguido de :// e um endereço.

O pesquisador têm como representação textual:

        NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO

Regras de validação:

    Nome, biografia, função, email e foto não podem ser vazios ou em branco

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio." (ex.: "Campo NOME nao pode ser nulo ou vazio."

      -        Atributo nao pode ser diferente de NOME, FUNCAO, BIOGRAFRIA, EMAIL e FOTO.  

      - Mensagem: “Atributo invalido.”

     - Atributo não pode ser vazio ou nulo.
              - Mensagem: “Atributo nao pode ser vazio ou nulo.”

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

Métodos da facade:

    void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL)
    void alteraPesquisador(String email, String atributo, String novoValor)

    atributo pode ser NOME, FUNCAO, BIOGRAFIA, EMAIL e FOTO

    void desativaPesquisador(String email)
    void ativaPesquisador(String email)
    String exibePesquisador(String email)
    boolean pesquisadorEhAtivo(String email)

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

O formato textual do problema é:

        CÓDIGO - DESCRIÇÃO - VIABILIDADE

O formato textual do objetivo é:

        CÓDIGO - TIPO - DESCRIÇÃO - VALOR

        (VALOR é a soma de ADERÊNCIA e VIABILIDADE)

Regras de validação:

    Nenhum campo pode ser vazio.

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio."

    Viabilidade e aderência devem ter valores entre 1 e 5

    Mensagem: "Valor invalido de NOME_DO_CAMPO."

    Não deve ser possível apagar ou exibir objetivos/problemas inexistentes

    Mensagem: "Problema nao encontrado"
    Mensagem: "Objetivo nao encontrado"

Métodos da facade:

    String cadastraProblema(String descricao, int viabilidade)
    String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade)
    void apagarProblema(String codigo)
    void apagarObjetivo(String codigo)
    String exibeProblema(String codigo)
    String exibeObjetivo(String codigo)

# Caso de Uso 4. Atividades Metodológicas

Para atingir um objetivo, especialmente os objetivos específicos, é importante descrever e planejar atividades a serem realizadas para obter resultados. Cada atividade planejada apresenta uma descrição do que deve ser feito, uma duração planejada, resultados esperados e um risco associado.

Toda atividade têm um risco associado. O risco pode ser em três níveis: BAIXO, MEDIO, ALTO. Além do nível, há uma descrição do que ocasiona o risco e o que deve ser feito para mitigar o risco.

Por fim, os resultados esperados são compostos de uma lista de itens que podem ser marcados como feitos ou não. Existem apenas dois estados para cada item: REALIZADO e PENDENTE. Toda lista começa, por padrão, com cada item marcado como PENDENTE.

Cada atividade é identificada pelo código A + valor começando a partir de 1, gerado automaticamente pelo sistema. Cada atividade é representada pela string:

DESCRIÇÃO (NIVEL_RISCO - DESC_RISCO) | REALIZADO - ITEM1 | REALIZADO - ITEM2 | PENDENTE - ITEM3

A ordem dos itens é determinada pela ordem de cadastro, não podem existir itens repetidos e cada item é identificado por sua ordem de cadastro. Também deve ser possível apagar uma atividade. Ao apagar uma atividade isso não altera o código a ser gerado para a próxima entidade.

Regras de validação:

    Nenhum campo pode ser vazio.

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio."

    Nivel de campo inválido.

    Mensagem: "Valor invalido do nivel do risco."

    Item vazio.

    Mensagem: "Item nao pode ser nulo ou vazio."

    Não deve ser possível apagar/exibir/trabalhar com atividades/itens inexistentes

    Mensagem: "Atividade nao encontrada"
    Mensagem: "Item nao encontrado"

Métodos da facade:

    String cadastraAtividade(String Descricao, String nivelRisco, String descricaoRisco)
    void apagaAtividade(String codigo)
    void cadastraItem(String codigo, String item)
    String exibeAtividade(String codigo)
    int contaItensPendentes(String codigo)
    int contaItensRealizados(String codigo)

# Caso de Uso 5. Associações de Objetivos e Problema

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 1

Os elementos da pesquisa estão implementados. Um problema descreve uma inquietação a ser resolvida. O objetivo define uma meta que, se atingida, irá ajudar a resolver o problema. Assim, uma pesquisa está ligada a um problema e também está associada a um, ou mais, objetivos.

Neste sentido, é necessário agora interligar os problemas e objetivos a pesquisas. Cada pesquisa deve estar associada a um problema apenas. Cada problema pode estar ligada a várias pesquisas e vários objetivos. Na pesquisa que estamos como exemplo, a problemática do discurso homofóbico em chats online de alunos de computação de primeiro período é abordada na tentativa de diminuir a frequência de mensagens homofóbicas trocadas em chats. Outra pesquisa pode abordar o mesmo problema, mas objetivando diminuir a quantidade de alunos homofóbicos com acesso aos chats.

Neste caso de uso, o Psquiza deve permitir a associar um problema a uma pesquisa. Uma pesquisa pode estar associada a um único problema. Mas o mesmo problema pode estar associado a várias pesquisas. Uma pesquisa pode estar associada a vários objetivos, entretanto, cada objetivo só pode estar associado a uma única pesquisa.

Além dessas associações, deve ser possível listar pesquisas usando três critérios distintos:

    Pesquisas com associações a problemas de maior ID aparecem primeiro. Depois, aparecem as pesquisas sem problemas associados (da pesquisa com maior ID até a pesquisa para a de menor ID) (ORDEM: PROBLEMA).
    Pesquisas com mais objetivos associados. Em caso de empate, listar primeiro a pesquisa com objetivo de maior ID. Para as pesquisas sem objetivos, listar da pesquisa de maior ID para a de menor ID (ORDEM: OBJETIVOS).
    Pesquisas do maior ID para o de menor ID (ORDEM: PESQUISA).

A listagem deve aparecer da seguinte forma:

        Lista de pesquisas (ORDEM):

* CÓDIGO - Descrição - Campo de interesse | CÓDIGO - Descrição - Campo de interesse | CÓDIGO - Descrição - Campo de interesse

Regras de validação:

    Só deve ser possível alterar, encerrar, ativar e exibir pesquisas já cadastradas.

    Mensagem: "Pesquisa nao encontrada."

    Não deve permitir associar a uma pesquisa já desativada

    Mensagem: "Pesquisa desativada."

    Não deve ser possível associar um segundo problema a uma pesquisa.

    Mensagem: "Pesquisa ja associada a um problema."

    Não deve ser possível associar um objetivo a uma segunda pesquisa.

    Mensagem: "Objetivo ja associado a uma pesquisa."

    Observações:

    Ao tentar associar um problema já associado ou desassociar um problema não associado, basta retornar "false" a chamada
    Ao associar um objetivo já associado a pesquisa ou desassociar um objetivo não associado, basta retornar "false" a chamada

Métodos da facade:

    boolean associaProblema(String idPesquisa, String idProblema) // retorna sucesso
    boolean desassociaProblema(String idPesquisa) // retorna sucesso
    boolean associaObjetivo(String idPesquisa, String idObjetivo) // retorna sucesso
    boolean desassociaObjetivo(String idPesquisa, String idObjetivo) // retorna sucesso
    String listaPesquisas(String ordem) // Ordem é PROBLEMA, OBJETIVOS, PESQUISA

# Caso de Uso 6. Associação e Especialização da Pesquisadora

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 2

Existem diferentes categorias de pesquisadores e diferentes informações associadas a tais pesquisadores. Cada pesquisador também pode estar associado a diferentes pesquisas.

Cada pesquisador é cadastrado como um usuário externo, aluno ou professor sem características além das definidas anteriormente. No entanto, é possível cadastrar detalhes da especialidade ao pesquisador da função "professora" ou "aluno". Um professor têm detalhes como formação, unidade alocada e data de contratação. A aluna têm semestre de ingresso, Índice de Eficiência Acadêmica (IEA). Deve ser possível remover a especialidade da pesquisadora, caso o sistema ache necessário. Os detalhes da especialização também são removidos caso a função do pesquisador mude.

Ao cadastrar uma especialização, altera-se a maneira que o pesquisador é exibido de acordo com a tabela abaixo:

Especialidade
	

Atributos Específicos
	

Representação

Aluna, Externo, Professora sem especialidade
	

-
	

NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO

Aluna
	

semestre, IEA
	

NOME (ALUNO) - BIOGRAFIA - EMAIL - FOTO - 1o SEMESTRE - 5,5

Professora
	

formação, unidade, data
	

NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO - Doutorado - UASC - 03/03/2015

Além de ser possível remover uma especialização, deve ser possível listar pesquisadores por tipo, além de associar os pesquisadores a pesquisas.

Regras de validação:

    Só deve ser possível associar/desassociar a pesquisas existentes:

    Mensagem: "Pesquisa nao encontrada."

    Não deve permitir associar/dessaociar uma pesquisa já desativada

    Mensagem: "Pesquisa desativada."

    Só deve ser possível associar/desassociar/cadastrar/especialidades em pesquisadoras existentes:

    Mensagem: "Pesquisadora nao encontrada."

    Só deve ser possível listar pesquisadores de um tipo existente:

    Mensagem: "Tipo TIPO inexistente."

    Só deve ser possível cadastrar uma especialidade no pesquisador do TIPO correto(ex: Não pode cadastrar uma especialidade de aluno em um professor ou em um externo, apenas em alunos.)

    Mensagem:”Pesquisador nao compativel com a especialidade.” 

    Formato inválido dos atributos (semestre menor do que 1; IEA entre 0.0 e 10.0, inclusive; data válida no formato DD/MM/AAAA)

    Mensagem: "Atributo ATRIBUTO com formato invalido."
    Atributos não podem ser vazios ou em branco

    Mensagem: "Campo NOME_DO_CAMPO nao pode ser nulo ou vazio." (ex.: "Campo IEA nao pode ser nulo ou vazio.")

    Observações:

    Ao tentar associar um pesquisador já associado ou desassociar um pesquisador não associado, basta retornar "false" a chamada

Métodos da facade:

    boolean associaPesquisador(String idPesquisa, String emailPesquisador)
    boolean desassociaPesquisador(String idPesquisa, String emailPesquisador)
    void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data)
    void cadastraEspecialidadeAluno(String email, int semestre, double IEA)
    String listaPesquisadores(String tipo) // TIPO pode ser "EXTERNO", "ALUNA", "PROFESSORA"
    void alteraPesquisador(String email, String atributo, String novoValor)

    atributo pode ser também SEMESTRE, IEA, FORMACAO, UNIDADE, DATA

    String exibePesquisador(String email) // Método afetado quando especialidade é implementada

# Caso de Uso 7. Associação e Execução de Atividades

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 4

Uma pesquisa não costuma ser um processo trivial. Ela, por vezes, envolve diferentes participantes e atividades, e é preciso acompanhar o que foi feito, o que está sendo realizado e conseguir capturar os resultados produzidos.

Para que uma atividade seja executada, ela precisa estar associada a uma pesquisa. Além disso, durante a execução é preciso determinar o item a ser executado e a quantidade de horas gastas nessa execução, bem como cadastrar os resultados obtidos por item da atividade.

Deve ser possível remover o resultado de uma atividade, bem como alterar sua duração e observações. A execução vale para a atividade independente de qual ou quais pesquisas ela está associada. Ao executar um item de uma atividade, o mesmo é considerado como REALIZADO.

Regras de validação:

    Só deve ser possível associar/desassociar a pesquisas existentes:

    Mensagem: "Pesquisa nao encontrada."

      -        Só deve ser possivel executar atividades que já estejam associadas a alguma pesquisa:

              -        Mensagem: “Atividade sem associacoes com pesquisas.”

    Não deve permitir associar/dessaociar uma pesquisa já desativada

    Mensagem: "Pesquisa desativada."

    Só deve ser possível associar/desassociar em atividades existentes:

    Mensagem: "Atividade nao encontrada."

    Só deve ser possível remover/exibir em resultados existentes:

    Mensagem: "Resultado nao encontrado."

    Não deve permitir executar um item já executado:

    Mensagem: “Item ja executado.”

    Formato inválido dos atributos (não podem ser vazios ou em branco ou duração nula ou menor que 1)

    Mensagem: "Resultado nao pode ser nulo ou vazio."
    Mensagem: "Duracao nao pode ser nula ou negativa."

      -         Observações:

    Ao tentar associar uma atividade já associada basta retornar "false" a chamada

Métodos da facade:

    boolean associaAtividade(String codigoPesquisa, String codigoAtividade)
    boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade)
    void executaAtividade(String codigoAtividade, int item, int duracao)
    int cadastraResultado(String codigoAtividade, String resultado)
    boolean removeResultado(String codigoAtividade, int numeroResultado)
    String listaResultados(String codigoAtividade)
    int getDuracao(String codigoAtividade)

# Caso de Uso 8. Busca por Palavra-chave

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 3

Um sistema só está completo se for possível encontrar facilmente os elementos cadastrados no sistema.

Esse é aquele caso de uso, fácil de ser descrito, mas não necessariamente fácil de ser implementado. :)

O objetivo deste caso de uso é procurar por palavras entre as diferentes entidades no sistema. Mais especificamente, deve se procurar a palavra-chave nos seguintes campos:

    Pesquisa: Descrição e Campo de interesse
    Pesquisador: Biografia
    Problemas: Descrição
    Objetivo: Descrição
    Atividade: Descrição e Descrição do Risco

A palavra buscada pode estar PARCIALMENTE presente no campo procurado. Por exemplo, a chave de busca 'Homo' existe na descrição 'Discriminação em relação a grupos homoafetivos' e na descrição 'Estudando a relação filogenética do homo-sapiens'. Deve ser ignorada a capitalização dos campos buscados bem como da palavra de busca. A acentuação das palavras deve ser considerada na busca.

Por padrão, deve ser retornado o código da entidade que contém a palavra chave e o campo com a palavra-chave na ordem: Pesquisa(Descrição depois Campo de interesse), Pesquisador, Problemas, Objetivo e Atividade(Descrição depois Descrição do Risco), usando a ordem anti-lexicográfica do código que a identifica. Observe que uma atividade e uma pesquisa podem aparecer DUAS vezes nos resultados de pesquisa, uma para cada campo associado (Descrição e Campo de Interesse, ou Descrição e Descrição do Risco).

Além da exibição em lista dos resultados, deve ser possível pegar um resultado específico (em ordem) e contabilizar o total de resultados.

Exemplo, na busca por "amo":

    SOC1: Avaliação da aceitação das relações de poliamor na sociedade líquida | P1: A baixa aceitação do amor homoafetivo no contexto virtual-social | O1: Comparar as referências ao Deus Amon comparado com Horbehutet  | A1: Analisar o uso da palavra amo nos textos extraídos 

Ao buscar o termo “amo” com número 2, retornaria: “P1: A baixa aceitação do amor homoafetivo no contexto virtual-social -

4321         ”. E ao contabilizar o total de resultado deveria retornar 4.

Regras de validação:

    Termo de busca vazio ou número do resultado inválido (menor do que zero)

    Mensagem: "Campo termo nao pode ser nulo ou vazio."
    Mensagem: "Numero do resultado nao pode ser negativo."

    Número do resultado inexistente:

    Mensagem: "Entidade nao encontrada."

Métodos da facade:

    String busca(String termo)

    String busca(String termo, int numeroDoResultado)
    int contaResultadosBusca(String termo)

# Caso de Uso 9. Ordem das Atividades

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 7

Durante a execução de atividades pode ser do interesse do pesquisador definir uma ordem de execução para representar uma lógica de execução das atividades.

Esta ordem de execução não faz, necessariamente, que uma atividade só possa ser executada depois de outra atividade definida anteriormente. A ordem é usada como um mecanismo de sugestão e planejamento, mas não altera como as atividades são executadas.

Ao definir uma ordem, deve ser possível navegar nas atividades buscando, a partir da atividade consultada, qual o risco mais elevado associado à execução daquela atividade e as que se seguem. Caso não exista próximas atividades ao chamar pegaMaiorRiscoAtividades, retorne a mensagem “Nao existe proxima atividade.”

Cada atividade só pode ter uma atividade subsequente, apesar de que a mesma atividade pode ter uma ou mais atividades que a precedem. Por fim, deve ser possível contar quantas atividades estão planejadas depois da atividade atual a ser consultada. Importante: considere a sequência de atividades 1 → 2 → 3 → 4, se for retirada a próxima atividade da atividade 2, isso se quebra em duas sequências: 1 → 2 e 3 → 4.

Regras de validação:

    Só deve ser possível definir/tirar ordem de atividades existentes:

    Mensagem: "Atividade nao encontrada."

    Formato inválido dos atributos (não podem ser vazios ou em branco ou enésima atividade menor que 1)

    Mensagem: "Atividade nao pode ser nulo ou vazio."
    Mensagem: "EnesimaAtividade nao pode ser negativa ou zero."

    Enésima atividade inexistente na cadeia de atividades:

    Mensagem: "Atividade inexistente."

      -        Não deve ser possível definir uma segunda próxima atividade:

      -        Mensagem:”Atividade ja possui uma subsequente.”

      -        Observações:

    Não deve ser possível a criação de loops.

    Mensagem:”Criacao de loops negada.”

Métodos da facade:

    void defineProximaAtividade(String idPrecedente, String idSubsquente)
    void tiraProximaAtividade(String idPrecedente)  // sem efeito em uma atividade sem próxima atividade
    int contaProximos(String idPrecedente) // 0 quando não tiver próximo
    String pegaProximo(String idAtividade, int enesimaAtividade)
    String pegaMaiorRiscoAtividades(String idAtividade)

# Caso de Uso 10. Próxima Atividade

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 5

Uma das funções mais importantes de um sistema é oferecer inteligência aos seus usuários. Essa inteligência deve facilitar a vida dos usuários e agilizar o projeto de pesquisa.

O sistema deve oferecer uma sugestão de próxima atividade a ser realizada dentro de uma pesquisa. Por padrão, a próxima atividade a ser sugerida deve ser sempre a mais antiga atividade com itens pendentes (estratégia: MAIS_ANTIGA).

Além da estratégia de MAIS_ANTIGA, deve ser possível configurar e utilizar outras estratégia de detecção da próxima atividade com itens pendentes. Toda estratégia têm como critério de desempate retornar a atividade mais antiga. São estratégias possíveis:

    MAIS_ANTIGA, já citada;
    MENOS_PENDENCIAS retornar a atividade com menos itens pendentes;
    MAIOR_RISCO retornar a atividade com maior risco pendente;
    MAIOR_DURACAO retornar a atividade na qual há maior duração de itens realizados.

Regras de validação:

    Código da pesquisa não pode ser vazia ou em branco

    Mensagem: "Pesquisa nao pode ser nula ou vazia."

    Estratégia não pode ser vazia ou em branco

    Mensagem: "Estrategia nao pode ser nula ou vazia."

    Só deve ser exibir próximo em pesquisas cadastradas.

    Mensagem: "Pesquisa nao encontrada."

    Não deve permitir buscar próximo em uma pesquisa já desativada

    Mensagem: "Pesquisa desativada."

    Só deve ser possível retornar próxima atividade de uma pesquisa que têm atividades com itens pendentes

    Mensagem: "Pesquisa sem atividades com pendencias."

Métodos da facade:

    void configuraEstrategia(String estrategia)
    String proximaAtividade(String codigoPesquisa)

# Caso de Uso 11. Resultados

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 7

O sistema também deve ser útil para se comunicar com outros sistemas ou usuários externos.

Para permitir que usuários foram do sistema tenham acesso aos resultados da pesquisa e que se possa anexar o estado atual de uma pesquisa em processos e publicações, seu sistema deve permitir exportar uma pesquisa em arquivo texto. Seja para mostrar o estado atual de uma pesquisa, seja para mostrar os resultados já obtidos em uma pesquisa.

O formato do arquivo texto deve ser como descrito abaixo (tabulações de 4 espaços). Todas as entidades são ordenadas pela ordem de cadastro (e não de associação). Deve ser gerado um arquivo por consulta. Caso o arquivo já exista, o mesmo deve ser sobrescrito. O arquivo deve ser salvo na raíz do projeto e com o nome _CODIGO.txt"

- Pesquisa: CÓDIGO - Descrição - Campo de interesse

    - Pesquisadores:

        - NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO - Detalhes

        - NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO - Detalhes

        - NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO - Detalhes

    - Problema:

        - CÓDIGO - DESCRIÇÃO - VIABILIDADE

    - Objetivos:

        - CÓDIGO - TIPO - DESCRIÇÃO - VALOR

    - Atividades:

        - DESCRIÇÃO (NIVEL_RISCO - DESC_RISCO)

            - REALIZADO - ITEM1

            - REALIZADO - ITEM2

            - PENDENTE - ITEM3

        - DESCRIÇÃO (NIVEL_RISCO - DESC_RISCO)

            - REALIZADO - ITEM4

            - REALIZADO - ITEM5

            - PENDENTE - ITEM6

Também deve ser possível gerar um arquivo com os resultados obtidos em uma pesquisa. O formato é descrito abaixo e o nome do arquivo é CÓDIGOPESQUISA-Resultados.txt. Também se aplica aqui as mesmas restrições caso o arquivo exista. Lembrar também que apenas itens realizados aparecem no resultado.

- Pesquisa: CÓDIGO - Descrição - Campo de interesse

    - Resultados:

        - DESCRIÇÃO

            - ITEM1 - DURAÇÃO

            - ITEM2 - DURAÇÃO

            - DESCRIÇÃO_RESULTADO1

            - DESCRIÇÃO_RESULTADO2

        - DESCRIÇÃO

            - ITEM1 - DURAÇÃO

            - ITEM2 - DURAÇÃO

            - DESCRIÇÃO_RESULTADO1

            - DESCRIÇÃO_RESULTADO2

Regras de validação:

    Código da pesquisa não pode ser vazia ou em branco

    Mensagem: "Pesquisa nao pode ser nula ou vazia."

    Só deve ser possível exportar pesquisas já cadastradas.

    Mensagem: "Pesquisa nao encontrada."

Métodos da facade:

    void gravarResumo(String codigoPesquisa)
    void gravarResultados(String codigoPesquisa)

# Caso de Uso 12. Persistência

LIMITAÇÃO: NÃO PODE SER FEITA PELO RESPONSÁVEL DO CDU 6

Até agora o sistema operou sem garantir a permanência de estado entre duas execuções do seu sistema. Isso limita bastante a utilidade do Psquiza, já que seria necessário garantir que o programa nunca irá travar e que nunca faltará energia.

Felizmente, existe uma estratégia simples para a permanência do estado da aplicação entre execuções, a persistência de dados. Persistir os dados significa guardar o estado atual do sistema em uma mídia permanente. Uma das estratégias mais simples de armazenamento é fazer uso de arquivos para isto.

Seu sistema deve armazenar as entidades básicas do sistema:

    Pesquisa
    Pesquisadores
    Objetivos
    Problemas
    Atividades

Bem como tornar permanente a relação das entidades e o que as compõe. Tenha cuidado pois isto significa, inclusive, armazenar a ordem e a configuração para recuperar a próxima atividade.

O sistema começa sempre vazio e deve ser iniciado ao executar a chamada carrega(). Ainda, o sistema só precisa ser salvo ao realizar  a chamada salva(). O sistema deve sobrescrever qualquer arquivo que exista previamente durante a operação de salvar.

Métodos da facade:

    void salva()
    void carrega()



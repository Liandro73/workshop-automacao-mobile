#language:pt
#enconding:utf-8

  @Regressivo @Alarme
  Funcionalidade: Alarme

    @FluxoFeliz @AdicionarAlarme
    Cenário:  Adicionar alarme com sucesso
      Dado que eu esteja na tela inicial do App relógio
      Quando eu clicar em adicionar um alarme
      E preencher todos os requisitos
      Então devo validar que foi adicionado um alarme com sucesso
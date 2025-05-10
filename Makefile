.DEFAULT=ajuda

.PHONY: ajuda
ajuda:
	@echo "Makefile para construir e executar o projeto"
	@echo ""
	@echo "Uso:"
	@echo "  make iniciar     Inicia o projeto usando Docker Compose"
	@echo "  make parar       Para todos os containers do projeto"
	

.PHONY: iniciar
iniciar:
	@echo "Iniciando o projeto usando Docker Compose ..."
	@docker-compose up -d
	@echo "Acesso ao AKHQ em http://localhost:8080. Pode levar alguns minutos para iniciar."

.PHONY: parar
    parar:
    @echo "Parando todos os containers do projeto ..."
    @docker-compose down --remove-orphans
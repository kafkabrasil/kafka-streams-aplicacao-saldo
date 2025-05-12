.DEFAULT=help

.PHONY: help
help:
	@echo "Makefile para construir e executar o projeto"
	@echo ""
	@echo "Uso:"
	@echo "  make start     Inicia o projeto usando Docker Compose"
	@echo "  make stop       Para todos os containers do projeto"
	@echo "  make cleanup    Remove todos os dados do projeto"
	

.PHONY: start
start:
	@echo "Iniciando o projeto usando Docker Compose ..."
	@docker-compose up -d
	@echo "Acesso ao AKHQ em http://localhost:8080. Pode levar alguns minutos para iniciar."

.PHONY: stop
stop:
	@echo "Parando todos os containers do projeto ..."
	@docker-compose down --remove-orphans
	@echo "Todos os containers foram parados."

.PHONY: cleanup
cleanup:
	@echo "Removendo todos os dados do projeto ..."
	@rm -rf ./kafka_data ./zoo_data
	@echo "Todos os dados foram removidos."
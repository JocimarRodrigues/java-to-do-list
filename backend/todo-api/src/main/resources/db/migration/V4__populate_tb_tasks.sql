-- Inserindo tarefas com status como string ('PENDING' ou 'FINISH')
INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Criar fluxo UML', 'PENDING', 1, 'Criar o fluxo uml da aplicação', CURRENT_TIMESTAMP),
('Criar o BackEnd da Aplicação', 'PENDING', 1, 'Criar Backend, persistência e autenticação', CURRENT_TIMESTAMP),
('Criar o FrontEnd da Aplicação', 'PENDING', 1, 'Criar Frontend da aplicação', CURRENT_TIMESTAMP),
('Integrar FrontEnd com BackEnd', 'PENDING', 1, 'Integrar backend com frontend', CURRENT_TIMESTAMP),
('Aplicar segurança a autentificação dos usuários', 'FINISH', 1, 'Aplicar segurança na autentificação dos usuários, usar spring security para tal, usar jwt', CURRENT_TIMESTAMP),
('Subir aplicação para produção', 'FINISH', 1, 'Subir aplicação para ambiente de produção (Criar ambiente de produção e ambiente de desenvolvimento)', CURRENT_TIMESTAMP),
('Criar documentação do projeto', 'PENDING', 1, 'Escrever documentação detalhada sobre as funcionalidades do sistema e seu uso', CURRENT_TIMESTAMP),
('Realizar testes unitários', 'PENDING', 1, 'Desenvolver testes unitários para garantir a qualidade do código', CURRENT_TIMESTAMP),
('Configurar CI/CD', 'PENDING', 1, 'Configurar pipeline de integração e entrega contínua para o projeto', CURRENT_TIMESTAMP),
('Criar scripts de banco de dados', 'FINISH', 1, 'Desenvolver scripts SQL para criação e manutenção do banco de dados', CURRENT_TIMESTAMP),
('Gerar relatórios automáticos', 'PENDING', 1, 'Implementar funcionalidade para gerar relatórios automáticos baseados nos dados do sistema', CURRENT_TIMESTAMP),
('Configurar autenticação social', 'CANCELED', 1, 'Adicionar autenticação via Google e Facebook para usuários', CURRENT_TIMESTAMP),
('Otimizar desempenho do sistema', 'FINISH', 1, 'Identificar e corrigir gargalos de desempenho no sistema', CURRENT_TIMESTAMP),
('Criar ambiente de homologação', 'PENDING', 1, 'Configurar ambiente de homologação para testes pré-produção', CURRENT_TIMESTAMP),
('Desenvolver funcionalidade de notificações', 'PENDING', 1, 'Adicionar notificações push para eventos importantes no sistema', CURRENT_TIMESTAMP),
('Realizar revisão de código', 'FINISH', 1, 'Fazer revisão de código para garantir boas práticas e qualidade', CURRENT_TIMESTAMP),
('Configurar monitoramento da aplicação', 'PENDING', 1, 'Configurar ferramentas de monitoramento, como Prometheus e Grafana', CURRENT_TIMESTAMP),
('Criar integração com serviço de pagamentos', 'PENDING', 1, 'Implementar integração com um gateway de pagamentos', CURRENT_TIMESTAMP),
('Melhorar usabilidade da interface', 'FINISH', 1, 'Ajustar design e fluxo da interface para melhor experiência do usuário', CURRENT_TIMESTAMP),
('Implementar sistema de permissões', 'PENDING', 1, 'Criar sistema de controle de acesso baseado em roles', CURRENT_TIMESTAMP),
('Atualizar dependências do projeto', 'CANCELED', 1, 'Atualizar todas as dependências para suas versões mais recentes', CURRENT_TIMESTAMP),
('Criar mecanismo de busca interna', 'PENDING', 1, 'Implementar um mecanismo de busca eficiente para o sistema', CURRENT_TIMESTAMP),
('Testar acessibilidade do sistema', 'FINISH', 1, 'Garantir que o sistema seja acessível para pessoas com deficiência', CURRENT_TIMESTAMP),
('Realizar teste de carga', 'PENDING', 1, 'Executar testes de carga para avaliar desempenho em alta demanda', CURRENT_TIMESTAMP),
('Implementar backup automático', 'PENDING', 1, 'Criar sistema de backup automático para os dados críticos', CURRENT_TIMESTAMP);

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Organizar evento corporativo', 'PENDING', 2, 'Planejar e organizar evento para lançamento de novo produto', NOW()),
('Revisar contrato de fornecedores', 'PENDING', 2, 'Analisar e revisar contratos com novos fornecedores para o próximo trimestre', NOW()),
('Criar campanha de marketing', 'PENDING', 2, 'Desenvolver nova campanha de marketing para divulgação nas redes sociais', NOW()),
('Atualizar manual de políticas da empresa', 'PENDING', 2, 'Revisar e atualizar manual de políticas internas da empresa', NOW()),
('Contratar equipe de suporte técnico', 'FINISH', 2, 'Recrutar e selecionar novos membros para a equipe de suporte técnico', NOW()),
('Realizar treinamento de integração para novos funcionários', 'FINISH', 2, 'Conduzir treinamento de integração para os novos colaboradores', NOW());

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Realizar análise de mercado', 'PENDING', 3, 'Analisar tendências de mercado e identificar novas oportunidades de negócios', NOW()),
('Criar plano de vendas para o próximo trimestre', 'PENDING', 3, 'Desenvolver plano estratégico de vendas para o próximo trimestre', NOW()),
('Ajustar layout da loja física', 'PENDING', 3, 'Reorganizar o layout da loja física para melhorar a experiência do cliente', NOW()),
('Renovar contratos de clientes-chave', 'PENDING', 3, 'Negociar e renovar contratos com os principais clientes da empresa', NOW()),
('Organizar reunião com stakeholders', 'FINISH', 3, 'Organizar reunião com os principais stakeholders para discutir metas anuais', NOW()),
('Realizar pesquisa de satisfação com clientes', 'FINISH', 3, 'Aplicar pesquisa de satisfação com clientes para entender a experiência deles com nossos serviços', NOW());

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Desenvolver nova identidade visual', 'PENDING', 4, 'Criar nova identidade visual para o rebranding da empresa', NOW()),
('Criar conteúdo para blog corporativo', 'PENDING', 4, 'Escrever artigos semanais para o blog da empresa com foco em tendências do setor', NOW()),
('Organizar sessão de fotos para catálogo de produtos', 'PENDING', 4, 'Organizar e dirigir sessão de fotos para novo catálogo de produtos', NOW()),
('Criar estratégia de mídia paga', 'PENDING', 4, 'Elaborar estratégia de anúncios pagos para aumentar a visibilidade da marca', NOW()),
('Atualizar portfólio de design', 'FINISH', 4, 'Revisar e atualizar o portfólio de design com os projetos mais recentes', NOW()),
('Produzir vídeos institucionais', 'FINISH', 4, 'Produzir e editar vídeos institucionais para a comunicação interna e externa', NOW());

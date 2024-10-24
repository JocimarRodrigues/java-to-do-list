INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Criar fluxo UML', 0, 1, 'Criar o fluxo uml da aplicação', NOW()),
('Criar o BackEnd da Aplicação', 0, 1, 'Criar Backend, persistência e autenticação', NOW()),
('Criar o FrontEnd da Aplicação', 0, 1, 'Criar Frontend da aplicação', NOW()),
('Integrar FrontEnd com BackEnd', 0, 1, 'Integrar backend com frontend', NOW()),
('Aplicar segurança a autentificação dos usuários', 1, 1, 'Aplicar segurança na autentificação dos usuários, usar spring security para tal, usar jwt', NOW()),
('Subir aplicação para produção', 1, 1, 'Subir aplicação para ambiente de produção (Criar ambiente de produção e ambiente de desenvolvimento)', NOW());

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Organizar evento corporativo', 0, 2, 'Planejar e organizar evento para lançamento de novo produto', NOW()),
('Revisar contrato de fornecedores', 0, 2, 'Analisar e revisar contratos com novos fornecedores para o próximo trimestre', NOW()),
('Criar campanha de marketing', 0, 2, 'Desenvolver nova campanha de marketing para divulgação nas redes sociais', NOW()),
('Atualizar manual de políticas da empresa', 0, 2, 'Revisar e atualizar manual de políticas internas da empresa', NOW()),
('Contratar equipe de suporte técnico', 1, 2, 'Recrutar e selecionar novos membros para a equipe de suporte técnico', NOW()),
('Realizar treinamento de integração para novos funcionários', 1, 2, 'Conduzir treinamento de integração para os novos colaboradores', NOW());

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Realizar análise de mercado', 0, 3, 'Analisar tendências de mercado e identificar novas oportunidades de negócios', NOW()),
('Criar plano de vendas para o próximo trimestre', 0, 3, 'Desenvolver plano estratégico de vendas para o próximo trimestre', NOW()),
('Ajustar layout da loja física', 0, 3, 'Reorganizar o layout da loja física para melhorar a experiência do cliente', NOW()),
('Renovar contratos de clientes-chave', 0, 3, 'Negociar e renovar contratos com os principais clientes da empresa', NOW()),
('Organizar reunião com stakeholders', 1, 3, 'Organizar reunião com os principais stakeholders para discutir metas anuais', NOW()),
('Realizar pesquisa de satisfação com clientes', 1, 3, 'Aplicar pesquisa de satisfação com clientes para entender a experiência deles com nossos serviços', NOW());

INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Desenvolver nova identidade visual', 0, 4, 'Criar nova identidade visual para o rebranding da empresa', NOW()),
('Criar conteúdo para blog corporativo', 0, 4, 'Escrever artigos semanais para o blog da empresa com foco em tendências do setor', NOW()),
('Organizar sessão de fotos para catálogo de produtos', 0, 4, 'Organizar e dirigir sessão de fotos para novo catálogo de produtos', NOW()),
('Criar estratégia de mídia paga', 0, 4, 'Elaborar estratégia de anúncios pagos para aumentar a visibilidade da marca', NOW()),
('Atualizar portfólio de design', 1, 4, 'Revisar e atualizar o portfólio de design com os projetos mais recentes', NOW()),
('Produzir vídeos institucionais', 1, 4, 'Produzir e editar vídeos institucionais para a comunicação interna e externa', NOW());

COMMIT;
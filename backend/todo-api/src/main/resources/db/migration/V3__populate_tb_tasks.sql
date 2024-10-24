INSERT INTO tb_tasks (name, status, user_id, description, created_at) VALUES
('Criar fluxo UML', 0, 6, 'Criar o fluxo uml da aplicação', NOW()),
('Criar o BackEnd da Aplicação', 0, 6, 'Criar Backend, persistência e autenticação', NOW()),
('Criar o FrontEnd da Aplicação', 0, 6, 'Criar Frontend da aplicação', NOW()),
('Integrar FrontEnd com BackEnd', 0, 6, 'Integrar backend com frontend', NOW()),
('Aplicar segurança a autentificação dos usuários', 1, 6, 'Aplicar segurança na autentificação dos usuários, usar spring security para tal, usar jwt', NOW()),
('Subir aplicação para produção', 1, 6, 'Subir aplicação para ambiente de produção (Criar ambiente de produção e ambiente de desenvolvimento)', NOW());

COMMIT;
alter table medico add ativo tinyint;
update medico set ativo = 1;

alter table paciente add ativo tinyint;
update paciente set ativo = 1;
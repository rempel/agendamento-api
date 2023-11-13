create table consulta(
    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data_hora datetime not null,

    primary key(id),
    constraint fk_consulta_medico foreign key(medico_id) references medico(id),
    constraint fk_consulta_paciente foreign key(paciente_id) references paciente(id)

)
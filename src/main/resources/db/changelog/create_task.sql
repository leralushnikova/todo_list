CREATE TABLE task
(
    id          serial not null primary key,
    description varchar(100) not null,
    status       integer not null
);
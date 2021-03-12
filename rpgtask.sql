create table if not exists blog
(
    blog_id      int auto_increment,
    blog_title   varchar(200)     null comment '博客标题',
    blog_text    longtext         null comment '博客正文',
    blog_commit  text             null comment '博客留言id',
    verify_state bit default b'0' not null comment '审核状态',
    constraint blog_blog_id_uindex
        unique (blog_id)
)
    comment '博客';

alter table blog
    add primary key (blog_id);

create table if not exists commit
(
    commit_id      int auto_increment,
    commit_user_id int      not null comment '留言用户id',
    commit_text    text     null comment '留言内容',
    commit_date    datetime null comment '留言时间',
    constraint commit_commit_id_uindex
        unique (commit_id)
)
    comment '留言';

alter table commit
    add primary key (commit_id);

create table if not exists message
(
    message_id        int auto_increment,
    message_user_id   int  null comment '消息发出者的id',
    message_text      text null comment '消息内容',
    message_target_id int  null comment '消息指向的任务id',
    constraint message_message_id_uindex
        unique (message_id)
)
    comment '通知消息';

alter table message
    add primary key (message_id);

create table if not exists task
(
    task_id         int auto_increment,
    task_title      varchar(50)          not null comment ' 任务标题',
    task_icon       int     default 0    not null comment '任务图标',
    task_desc       text                 null comment '任务描述',
    award_coin      int     default 0    not null comment '任务完成货币奖励',
    task_state      tinyint default 0    not null comment '任务的状态',
    publish_user_id int                  not null comment '发布者的id',
    receive_user_id int                  null comment '接收者的id',
    verify_state    bit     default b'0' not null comment '审核状态',
    constraint task_task_id_uindex
        unique (task_id)
)
    comment '任务';

alter table task
    add primary key (task_id);

create table if not exists user
(
    user_id   int auto_increment,
    user_type bit         not null comment '用户类型:0普通用户1管理员',
    user_name varchar(50) not null comment '用户账号',
    user_pass varchar(50) not null comment '用户密码',
    constraint user_user_id_uindex
        unique (user_id),
    constraint user_user_name_uindex
        unique (user_name),
    constraint user_user_pass_uindex
        unique (user_pass)
)
    comment '用户基本';

alter table user
    add primary key (user_id);

create table if not exists user_blog
(
    user_id int  not null,
    blog_id text null comment '用户博客id',
    constraint user_blog_user_id_uindex
        unique (user_id),
    constraint user_blog_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户博客';

alter table user_blog
    add primary key (user_id);

create table if not exists user_desc
(
    user_id   int              not null,
    nick_name varchar(50)      not null comment '昵称',
    sex       bit default b'0' not null comment '性别 : 0 男 1 女',
    icon      int              null comment '头像',
    signature text             null comment '个性签名',
    title     text             null comment '称号 JSON',
    coin      int default 0    not null comment '货币数量',
    exp       int default 0    not null comment '经验值',
    reg_time  datetime         not null comment '注册时间',
    constraint user_desc_user_id_uindex
        unique (user_id),
    constraint user_info_user_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
)
    comment '用户详细';

alter table user_desc
    add primary key (user_id);

create table if not exists user_follow
(
    user_id   int  not null
        primary key,
    follow_id text not null,
    constraint table_name_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户关注';

create table if not exists user_message
(
    user_id    int  not null
        primary key,
    message_id text not null comment '用户的消息内容id',
    constraint user_message_user_user_id_fk
        foreign key (user_id) references user (user_id)
)
    comment '用户消息';

create table if not exists user_task
(
    user_id          int      not null,
    last_signed_time datetime null comment '上次签到时间',
    signed_day       int      null comment '连续签到天数',
    publish_task_id  text     null comment '发布的任务id',
    doing_task_id    text     null comment '已完成的系统任务',
    finish_task_id   text     null comment '完成的任务的id',
    constraint user_task_user_id_uindex
        unique (user_id),
    constraint user_task_user_fk
        foreign key (user_id) references user (user_id)
            on update cascade on delete cascade
)
    comment '用户任务';

alter table user_task
    add primary key (user_id);




    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty datetime(6),
        kwota double precision,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    ) engine=InnoDB

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    ) engine=InnoDB

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    ) engine=InnoDB

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    ) engine=InnoDB

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    ) engine=InnoDB

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    ) engine=InnoDB

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    ) engine=InnoDB

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    ) engine=InnoDB

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    ) engine=InnoDB

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna bit,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    ) engine=InnoDB

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    ) engine=InnoDB

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    ) engine=InnoDB

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    ) engine=InnoDB

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    ) engine=InnoDB

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA (GRUPA_ID)

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC (RODZIC_ID)

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL (NAUCZYCIEL_ID)

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK (RZUTNIK_ID)

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW (ZESTAWSPRZETOW_ID)

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA (SALASPORTOWA_ID)

    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty datetime(6),
        kwota double precision,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    ) engine=InnoDB

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    ) engine=InnoDB

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    ) engine=InnoDB

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    ) engine=InnoDB

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    ) engine=InnoDB

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    ) engine=InnoDB

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    ) engine=InnoDB

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    ) engine=InnoDB

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    ) engine=InnoDB

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna bit,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    ) engine=InnoDB

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    ) engine=InnoDB

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    ) engine=InnoDB

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    ) engine=InnoDB

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    ) engine=InnoDB

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA (GRUPA_ID)

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC (RODZIC_ID)

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL (NAUCZYCIEL_ID)

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK (RZUTNIK_ID)

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW (ZESTAWSPRZETOW_ID)

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA (SALASPORTOWA_ID)

    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty datetime(6),
        kwota double precision,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    ) engine=InnoDB

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    ) engine=InnoDB

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    ) engine=InnoDB

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    ) engine=InnoDB

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    ) engine=InnoDB

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    ) engine=InnoDB

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    ) engine=InnoDB

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    ) engine=InnoDB

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    ) engine=InnoDB

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna bit,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    ) engine=InnoDB

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    ) engine=InnoDB

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    ) engine=InnoDB

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    ) engine=InnoDB

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    ) engine=InnoDB

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA (GRUPA_ID)

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC (RODZIC_ID)

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL (NAUCZYCIEL_ID)

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK (RZUTNIK_ID)

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW (ZESTAWSPRZETOW_ID)

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA (SALASPORTOWA_ID)

    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty datetime(6),
        kwota double precision,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    ) engine=InnoDB

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    ) engine=InnoDB

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    ) engine=InnoDB

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    ) engine=InnoDB

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    ) engine=InnoDB

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    ) engine=InnoDB

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    ) engine=InnoDB

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    ) engine=InnoDB

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    ) engine=InnoDB

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna bit,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    ) engine=InnoDB

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    ) engine=InnoDB

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    ) engine=InnoDB

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    ) engine=InnoDB

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    ) engine=InnoDB

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA (GRUPA_ID)

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC (RODZIC_ID)

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL (NAUCZYCIEL_ID)

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK (RZUTNIK_ID)

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW (ZESTAWSPRZETOW_ID)

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA (SALASPORTOWA_ID)

    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty datetime(6),
        kwota double precision,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    ) engine=InnoDB

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    ) engine=InnoDB

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    ) engine=InnoDB

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    ) engine=InnoDB

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    ) engine=InnoDB

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    ) engine=InnoDB

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    ) engine=InnoDB

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji datetime(6),
        dataZakupu datetime(6),
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    ) engine=InnoDB

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    ) engine=InnoDB

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna bit,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    ) engine=InnoDB

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    ) engine=InnoDB

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    ) engine=InnoDB

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    ) engine=InnoDB

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    ) engine=InnoDB

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA (GRUPA_ID)

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC (RODZIC_ID)

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL (NAUCZYCIEL_ID)

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA (SALA_ID)

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK (RZUTNIK_ID)

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW (ZESTAWSPRZETOW_ID)

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO (DZIECKO_ID)

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA (SZKOLA_ID)

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO (MIASTO_ID)

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA (SALASPORTOWA_ID)

    create table CZESNE (
       CZESNE_ID bigint not null,
        dataOplaty timestamp,
        kwota double,
        DZIECKO_ID bigint,
        primary key (CZESNE_ID)
    )

    create table DZIECKO (
       DZIECKO_ID bigint not null,
        imie varchar(255),
        wiek integer,
        GRUPA_ID bigint,
        primary key (DZIECKO_ID)
    )

    create table Dziecko_Rodzic (
       DZIECKO_ID bigint not null,
        RODZIC_ID bigint not null,
        primary key (DZIECKO_ID, RODZIC_ID)
    )

    create table GRUPA (
       GRUPA_ID bigint not null,
        maxLiczbaDzieci integer,
        wiek integer,
        NAUCZYCIEL_ID bigint,
        SALA_ID bigint,
        primary key (GRUPA_ID)
    )

    create table KOMPUTER (
       KOMPUTER_ID bigint not null,
        dataWygasnieciaGwarancji timestamp,
        dataZakupu timestamp,
        specyfikacja varchar(255),
        SALA_ID bigint,
        primary key (KOMPUTER_ID)
    )

    create table MIASTO (
       MIASTO_ID bigint not null,
        gmina varchar(255),
        nazwa varchar(255),
        powiat varchar(255),
        wojewodztwo varchar(255),
        primary key (MIASTO_ID)
    )

    create table NAUCZYCIEL (
       NAUCZYCIEL_ID bigint not null,
        adres varchar(255),
        email varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        stopien varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        SZKOLA_ID bigint,
        primary key (NAUCZYCIEL_ID)
    )

    create table RODZIC (
       RODZIC_ID bigint not null,
        adres varchar(255),
        imie varchar(255),
        nazwisko varchar(255),
        telefon integer,
        MIASTO_ID bigint,
        primary key (RODZIC_ID)
    )

    create table RZUTNIK (
       RZUTNIK_ID bigint not null,
        dataWygasnieciaGwarancji timestamp,
        dataZakupu timestamp,
        jakoscObrazu varchar(255),
        model varchar(255),
        primary key (RZUTNIK_ID)
    )

    create table SALA (
       SALA_ID bigint not null,
        liczbaKrzesel integer,
        liczbaLawek integer,
        RZUTNIK_ID bigint,
        SZKOLA_ID bigint,
        primary key (SALA_ID)
    )

    create table SALASPORTOWA (
       SALASPORTOWA_ID bigint not null,
        czyTrybuna boolean,
        wielkosc integer,
        SZKOLA_ID bigint,
        primary key (SALASPORTOWA_ID)
    )

    create table SPRZET (
       SPRZET_ID bigint not null,
        ilosc integer,
        nazwa varchar(255),
        ZESTAWSPRZETOW_ID bigint,
        primary key (SPRZET_ID)
    )

    create table SZAFKA (
       SZAFKA_ID bigint not null,
        haslo varchar(255),
        numer integer,
        pojemnosc integer,
        DZIECKO_ID bigint,
        SZKOLA_ID bigint,
        primary key (SZAFKA_ID)
    )

    create table SZKOLA (
       SZKOLA_ID bigint not null,
        adres varchar(255),
        nazwa varchar(255),
        patron varchar(255),
        MIASTO_ID bigint,
        primary key (SZKOLA_ID)
    )

    create table ZESTAWSPRZETOW (
       ZESTAWSPRZETOW_ID bigint not null,
        dyscyplina varchar(255),
        SALASPORTOWA_ID bigint,
        primary key (ZESTAWSPRZETOW_ID)
    )
create sequence hibernate_sequence start with 1 increment by 1

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA
create sequence hibernate_sequence start with 1 increment by 1

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA
create sequence hibernate_sequence start with 1 increment by 1

    alter table CZESNE 
       add constraint FKfygh3f0cxkep5h54jj921fflw 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table DZIECKO 
       add constraint FKhdjdk0bptuhn0pgylgecyux4v 
       foreign key (GRUPA_ID) 
       references GRUPA

    alter table Dziecko_Rodzic 
       add constraint FKt0ra64vfp19xevv82l5454s5g 
       foreign key (RODZIC_ID) 
       references RODZIC

    alter table Dziecko_Rodzic 
       add constraint FKohc6bvlwcom10xt146b6hwivd 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table GRUPA 
       add constraint FKpa9f9cbpg7kkryu44hocotiha 
       foreign key (NAUCZYCIEL_ID) 
       references NAUCZYCIEL

    alter table GRUPA 
       add constraint FKbojrc2n76j87lhubpmr69g7br 
       foreign key (SALA_ID) 
       references SALA

    alter table KOMPUTER 
       add constraint FKhtx94gl9lsx2f0aa67vt07jja 
       foreign key (SALA_ID) 
       references SALA

    alter table NAUCZYCIEL 
       add constraint FKi6xscj667dxqqql9q1fxky12q 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table NAUCZYCIEL 
       add constraint FKslg9k1op8v8mks56k39irybxf 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table RODZIC 
       add constraint FKl27hrnjwebuyl4hp4r28ywjqy 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table SALA 
       add constraint FKsdqektrqb6iv1ftb040sbstaw 
       foreign key (RZUTNIK_ID) 
       references RZUTNIK

    alter table SALA 
       add constraint FK223qkqhkuuoctagq56r9bpjqm 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SALASPORTOWA 
       add constraint FKfb1bhgtteehvojtj4n3xrunsx 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SPRZET 
       add constraint FKgh3nijonkvdolkeld6i193xkx 
       foreign key (ZESTAWSPRZETOW_ID) 
       references ZESTAWSPRZETOW

    alter table SZAFKA 
       add constraint FKr4lw4kby1nkj1a70hlwsmqtjk 
       foreign key (DZIECKO_ID) 
       references DZIECKO

    alter table SZAFKA 
       add constraint FKfxpyamq0jsvxbrgus55ly1hyr 
       foreign key (SZKOLA_ID) 
       references SZKOLA

    alter table SZKOLA 
       add constraint FKei6ruwli07f8c8ft11fm3v6nx 
       foreign key (MIASTO_ID) 
       references MIASTO

    alter table ZESTAWSPRZETOW 
       add constraint FKoyhv4icraa6witq6863rbweeo 
       foreign key (SALASPORTOWA_ID) 
       references SALASPORTOWA

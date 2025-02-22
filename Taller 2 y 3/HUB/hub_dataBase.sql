PGDMP                         y            hub_dataBase    13.2    13.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    49168    hub_dataBase    DATABASE     m   CREATE DATABASE "hub_dataBase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE "hub_dataBase";
                postgres    false            �            1259    49185    categorydamage    TABLE     �   CREATE TABLE public.categorydamage (
    categorydamage_id bigint NOT NULL,
    name character varying,
    color character varying
);
 "   DROP TABLE public.categorydamage;
       public         heap    postgres    false            �            1259    49193    costsrestoration    TABLE     �   CREATE TABLE public.costsrestoration (
    costsrestoration_id bigint NOT NULL,
    leveldamage_id bigint,
    typepainting_id bigint,
    categorydamage_id bigint,
    cost character varying
);
 $   DROP TABLE public.costsrestoration;
       public         heap    postgres    false            �            1259    49177    leveldamage    TABLE     d   CREATE TABLE public.leveldamage (
    leveldamage_id bigint NOT NULL,
    name character varying
);
    DROP TABLE public.leveldamage;
       public         heap    postgres    false            �            1259    49169    typepainting    TABLE     f   CREATE TABLE public.typepainting (
    typepainting_id bigint NOT NULL,
    name character varying
);
     DROP TABLE public.typepainting;
       public         heap    postgres    false            �            1259    49201    user    TABLE     }   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    name character varying,
    password character varying(255)
);
    DROP TABLE public."user";
       public         heap    postgres    false            �          0    49185    categorydamage 
   TABLE DATA           H   COPY public.categorydamage (categorydamage_id, name, color) FROM stdin;
    public          postgres    false    202   �       �          0    49193    costsrestoration 
   TABLE DATA           y   COPY public.costsrestoration (costsrestoration_id, leveldamage_id, typepainting_id, categorydamage_id, cost) FROM stdin;
    public          postgres    false    203   �       �          0    49177    leveldamage 
   TABLE DATA           ;   COPY public.leveldamage (leveldamage_id, name) FROM stdin;
    public          postgres    false    201   �       �          0    49169    typepainting 
   TABLE DATA           =   COPY public.typepainting (typepainting_id, name) FROM stdin;
    public          postgres    false    200          �          0    49201    user 
   TABLE DATA           9   COPY public."user" (user_id, name, password) FROM stdin;
    public          postgres    false    204   ;       :           2606    49192 "   categorydamage categorydamage_pkey 
   CONSTRAINT     o   ALTER TABLE ONLY public.categorydamage
    ADD CONSTRAINT categorydamage_pkey PRIMARY KEY (categorydamage_id);
 L   ALTER TABLE ONLY public.categorydamage DROP CONSTRAINT categorydamage_pkey;
       public            postgres    false    202            <           2606    49200 &   costsrestoration costsrestoration_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.costsrestoration
    ADD CONSTRAINT costsrestoration_pkey PRIMARY KEY (costsrestoration_id);
 P   ALTER TABLE ONLY public.costsrestoration DROP CONSTRAINT costsrestoration_pkey;
       public            postgres    false    203            8           2606    49184    leveldamage leveldamage_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.leveldamage
    ADD CONSTRAINT leveldamage_pkey PRIMARY KEY (leveldamage_id);
 F   ALTER TABLE ONLY public.leveldamage DROP CONSTRAINT leveldamage_pkey;
       public            postgres    false    201            6           2606    49176    typepainting typepainting_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.typepainting
    ADD CONSTRAINT typepainting_pkey PRIMARY KEY (typepainting_id);
 H   ALTER TABLE ONLY public.typepainting DROP CONSTRAINT typepainting_pkey;
       public            postgres    false    200            >           2606    49208    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    204            A           2606    49219 8   costsrestoration costsrestoration_categorydamage_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.costsrestoration
    ADD CONSTRAINT costsrestoration_categorydamage_id_fkey FOREIGN KEY (categorydamage_id) REFERENCES public.categorydamage(categorydamage_id);
 b   ALTER TABLE ONLY public.costsrestoration DROP CONSTRAINT costsrestoration_categorydamage_id_fkey;
       public          postgres    false    202    203    2874            @           2606    49214 5   costsrestoration costsrestoration_leveldamage_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.costsrestoration
    ADD CONSTRAINT costsrestoration_leveldamage_id_fkey FOREIGN KEY (leveldamage_id) REFERENCES public.leveldamage(leveldamage_id);
 _   ALTER TABLE ONLY public.costsrestoration DROP CONSTRAINT costsrestoration_leveldamage_id_fkey;
       public          postgres    false    2872    203    201            ?           2606    49209 6   costsrestoration costsrestoration_typepainting_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.costsrestoration
    ADD CONSTRAINT costsrestoration_typepainting_id_fkey FOREIGN KEY (typepainting_id) REFERENCES public.typepainting(typepainting_id);
 `   ALTER TABLE ONLY public.costsrestoration DROP CONSTRAINT costsrestoration_typepainting_id_fkey;
       public          postgres    false    2870    200    203            �      x�3��I-K�1�L�)M����� 7v�      �      x�3���4�\1z\\\ "�"      �      x������ � �      �      x�3�,�,H5����� �      �      x�3�LL��σ�\1z\\\ 8�     
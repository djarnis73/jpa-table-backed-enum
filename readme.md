##Tags
java, jpa, hibernate, spring-boot

##Background

I was working on a project where we were inserting lots of records in a database and I was looking for a way to persist [enums](https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html) efficiently.

The obvious solution is to use `@Enumerated(EnumType.ORDINAL)`, but I also wanted a way to translate the ordinal into the enum name.

This can of course be handled manually by creating a reference table etc. but I though it would be nice if the JPA layer could help create and populate the table.

## Enums as Entities
The JPA Spec ([JSR 338, section 2.1](https://download.oracle.com/otn-pub/jcp/persistence-2_2-mrel-eval-spec/JavaPersistence.pdf)) states that: 
>The entity class must have a no-arg constructor. The entity class may have other constructors as well.The no-arg constructor must be public or protected.
>
>The entity class must be a top-level class. An enum or interface must not be designated as an entity.
>
>The entity class must not be final. No methods or persistent instance variables of the entity class may be final.

However [Hibernate documentation](https://docs.jboss.org/hibernate/orm/5.4/userguide/html_single/Hibernate_User_Guide.html#entity-pojo) states that
> Hibernate, however, is not as strict in its requirements

So after some experimentation I managed to define an enum as an entity.

One of the key points is the EnumAsEntityInterceptor, which deals with instantiation, since an enum does not have a constructor
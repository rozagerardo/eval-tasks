# Spring Data JPA Ratings Library

This is a simple yet useful library to easily add "reviews" data to any of you JPA entities.

> **NOTE: This library is at it early development stage, so its functionality might be a little bit limited and probably not working for some project configurations**

### Configuration

Since we're just getting started developing this library, the easiest way to use it will be cloning or downloading the source code locally and installing it as a library in your local Maven repository (using the `mvn clean install`).

After this simple step you just have to add the library to your project:

```
<dependency>
	<groupId>com.baeldung</groupId>
	<artifactId>evaluation.ratings.lib</artifactId>
	<version>0.1.0</version>
</dependency>
```

And annotate your Boot main class or Configuration class with `@ReviewableSupport`.

With this, you're ready to start using our functionality.

### Make your entities "Reviewable"

In order to add the "Reviews" features to your entities, you just have to make them extend the `ReviewableEntity` class:

```
@Entity
public class MyEntity extends ReviewableEntity {
    // ...
}
```

### Adding Reviews

The usage is very simple, you just have to call the `addReview(Review)` method to the Reviewable entities whenever you want to register feedback for an entity, and naturally persist it afterwards.

The `Review` class is very simple, all you need to provide is a `rating` ranging from 0 to 100, and a text `description`.

### Showing the Reviews

We also provide a useful `ReviewableDto` class that can help you show the Reviews data more clearly.

Again, with just a very simple step you can use this feature, just extend your own DTOs with `ReviewableDto` providing the associated `ReviewableEntity` type as the generics type:

```
public class MyEntityDto extends ReviewableDto<MyEntity> {
    // ...
}
```

This will force you to use one of the `ReviewableDto` constructors in your DTO constructor. The simplest one will require just to pass in the `ReviewableEntity` as a parameter. And you can use `null` for the empty constructor.

A common DTO class will look something like this:

```
public class MyEntityDto extends ReviewableDto<MyEntity> {

    private Long id;
    private String entityField1;
    private String entityField2;

    private MyEntityDto() {
        super(null);
    }

    private MyEntityDto(MyEntity entity) {
        super(entity);
        this.id = entity.getId();
        this.firstName = entity.getField1();
        this.lastName = entity.getField2();
    }
```

### Customizing the reviews presentation

You can use the different `ReviewableDto` constructor options to customize how you want the reviews to be presented.

For instance, here we're indicating we want the reviews to be retrieved showing the higher rated comments first:

```
private MyEntityDto(MyEntity entity) {
    super(entity, ReviewsOrder.HIGHER);
    this.id = entity.getId();
    this.firstName = entity.getField1();
    this.lastName = entity.getField2();
}
```

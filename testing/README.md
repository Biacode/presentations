# Testing for complete beginners

```
  ____       _      ____     ____      _      __  __   ____  
 | __ )     / \    |  _ \   / ___|    / \    |  \/  | |  _ \
 |  _ \    / _ \   | |_) | | |       / _ \   | |\/| | | |_) |
 | |_) |  / ___ \  |  _ <  | |___   / ___ \  | |  | | |  __/
 |____/  /_/   \_\ |_| \_\  \____| /_/   \_\ |_|  |_| |_|    

```
### About me
```
Author: Arthur Asatryan
Software Engineer at SFL LLC.
Email: biacoder@gmail.com
GitHub: https://github.com/Biacode
Twitter: https://twitter.com/biacode
Linkedin: https://www.linkedin.com/in/arthur-asatryan/
```

## Preface
Some times new comers have some problems to understand essence of software testing.
* Most of time they have no idea about how to get started.
* Some don't know what benefits software testing gives them.
* They need some really basic newbie guide to get started.
* Some of them event think that the testing of **their own** application is not their business, because it's QA job.

## Real world example
Imagine situation when you need to investigate some library to deal with date and time.\
And you found that the [JodaTime](http://www.joda.org/joda-time/) is the best solution for you.

Let's say that you don't have existing project and starting from scratch.
1. You'l open your favorite IDE (in my case it is IntelliJ)
2. Create a new maven or gradle project (i will use gradle)
3. Adding joda-time dependency `compile group: 'joda-time', name: 'joda-time', version: '2.9.9'`
4. And the very first thing that you may want to do is writing a main method to test your new library while reading documentation etc...

Let's say we're implementing user access tokens `AccessToken` which has expiration date and time.

Suppose we have business requirements that we should restrict access if the expiration date is past.\
We can write an `AccessTokenService` which will be responsible for checking if the given access token is expired or not.

If we dive in to joda-time docs, we can find method called `isBeforeNow` which is exactly what we need.

```java
public boolean expired(final AccessToken accessToken) {
    return accessToken.getExpiration().isBeforeNow();
}
```

So after writing our first business logic we had following filling.

* Is the joda works correctly? (naive guy :D)
* Do I get it right? For beginners (and not only) the date operations are hard to understand.

You will write some code in your `main` method to test it out.

```java
final AccessToken accessToken = new AccessToken(UUID.randomUUID().toString(), DateTime.now().plusDays(3));
if (accessTokenService().expired(accessToken)) {
    System.out.println("The access token - " + accessToken + " is expired.");
} else {
    System.out.println("The access token - " + accessToken + " is not expired.");
}
```

If we run the application we will notice that the access token is not expired.

So everything goes as expected.\
Now if we want to push our code to production of course we don't want to have our main method there,
which is only for testing purposes.\
But we also want to have it somewhere, so in future if we need experiment more with this `unit` of logic we will write it again.

Here where the test package comes in.\
Any code written in this package will be excluded from production build.

So let's create a new class called `AccessTokenServiceImplTest` in test package.

It would be nice if we can have some library/framework which will execute our `unit test`.\
We will use [junit](http://junit.org/junit4/faq.html#overview_1) for this purpose.

Short about junit.

JUnit is a simple, open source framework to write and run repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks. JUnit features include:
> * Assertions for testing expected results
> * Test fixtures for sharing common test data
> * Test runners for running tests
> * JUnit was originally written by Erich Gamma and Kent Beck.

Now when we have some knowledge about junit framework let's write our first test.

We can (and should) cover at lest two cases.

One case is when the access token is not expired.
```java
@Test
public void testExpiresWhenTheAccessTokenIsNotExpired() {
    // test data
    final String token = UUID.randomUUID().toString();
    final DateTime expires = DateTime.now().plusDays(3);
    final AccessToken accessToken = new AccessToken(token, expires);
    // test scenario
    final boolean isExpired = accessTokenService.expired(accessToken);
    // assertions
    assertFalse(isExpired);
}
```

The another one as you may already guess is the case when our access token is expired.
```java
@Test
public void testExpiresWhenTheAccessTokenIsExpired() {
    // test data
    final String token = UUID.randomUUID().toString();
    final DateTime expires = DateTime.now().minusHours(2);
    final AccessToken accessToken = new AccessToken(token, expires);
    // test scenario
    final boolean isExpired = accessTokenService.expired(accessToken);
    // assertions
    assertTrue(isExpired);
}
```


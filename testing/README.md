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
We can write an `AccessTokenService` which will be responsible for checking if the given access token is expired or not.\
If we dive in to joda-time docs, we can find method called `isBeforeNow` which is exactly what we need.\
So after writing our first business logic we had following filling.

* Is the joda works correctly? (naive guy :D)
* Do I get it right? For beginners (and not only) the date operations are hard to understand.

You will write some code in your `main` method to test it out.\
Find working example in `MainApplication`\
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

We can **and should** cover at lest three cases.\
One case is when the access token is null.\
The second case is when access token is not expired.\
The another one as you may already guess is the case when our access token is expired.

## TDD - Test Driven Development
So what is TDD?

If short, TDD is when your production code is driven by **test first** approach.

TDD has 3 life cycles

* First we write failing test
* Then we write code which is some hov makes our failing test green.
* Then we make some refactoring.
* Continue cycle.

The benefits of this approach are

* We will not have any non covered code.
* Because of our laziness we will write our system components simple as possible.
* We will make our components decoupled and test them separately.

The list above is just small list of benefits which gives you TDD.

## Real world example using TDD principles
Suppose if we need a new method which returns the remaining days before our token will be expired.\
Let's call it `remainingDays`.\
If we follow to TDD principles we will write failing test first.\
We even have not written the `remainingDays` method in `AccessTokenService`

First we would cover the case when the given access token is null.\
Then we will check if the access token is expired and throw an `AccessTokenExpiredRuntimeException`.
The we will cover the case when the access token is not expired, and the remaining days are `7`.

Please see `AccessTokenServiceImplTest` for more details.

## Mocking
Now suppose if we have to retrieve our access token from some kind of Database.\
If case if we have relational database such as MySQL, then we should have JDBC and/or Hibernate to work with our DB.\
That's mean we will make lot of work just for testing our small application unit.\
Here where we need some framework, which will _Mock_ all implementation details from us, and return us exactly what we need.

For this purpose we will use [easymock](http://easymock.org/).

## Real world example using mocking principles
Let's say we should retrieve our access token from the database.\
For this we can write some method called `getByToken` which will accept the `token` and return `AccessToken` from the database.

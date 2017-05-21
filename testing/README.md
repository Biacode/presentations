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

Suppose we have business requirements that we should restrict access if the expiration date is past.

# What is mongoinit?
mongoinit is a Spring based (using spring-data) easily configurable Maven project which allows you to manage changes of your mongo documents and propagate these changes in sync with your code changes when you perform deployments.

It is mainly helpful for scenarios where you want to perform operation only once when your application starts like creating indexes, or pre-populating your DB with few inserts which should be done only once.

For further information and usage guidelines check out [the wiki](../../wiki/How-to-use-mongoinit).

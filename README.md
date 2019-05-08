# codeFellowship

## Day 1

### Controller and routes

`/signup` route built.
When used, user is brought to a signup page, where they can enter:
* username
* password
* First/last names
* date of birth
* bio

### Proper password hashing

implemented a password encoder, not quite sure how it works behind the scenes yet, but looking at my db, the password entered did get hashed.

### Error handling

a `userNotFound` exception was built and implemented. can be seen if you enter an unused id in the `/user/{id}` route

### User page w/image and basic info, using Thymeleaf appropriately
user's first/last names, DOB, and bio are all able to be seen at the `/user/{id}` route, along with a stock user silohette image

### Integration tests

still unfamiliar w/ these, would like additional walkthroughs

## Day 2 - Posts

### Ensure that your homepage, login, and registration routes are accessible to non-logged in users. All other routes should be limited to logged-in users.

I believe this was done in my WebSecurityConfig file, logged in users can see their page, non-logged in users can log in or sign up.

### Upon signing up or logging in, users should be taken to a /myprofile route that displays their information. Their profile page should display their posts.

This was done using a RedirectView built into both the sign in route and the log in routes.
* a new user signs up, is automatically logged in, and then redirected to their user page

* a current user logs in, and then is redirected to their user page

### Add a Post entity to your app with a body and a createdAt timestamp.

The post entity has been created and connected to the appUser table. **May need some more work.**

### A logged-in user should be able to create a Post, and a post should belong to the user that created it.

A post can be created by a user, and belongs to the user that created it. **Method may need additional work.**

### When a user is logged in, the app should display the user’s username on every page (probably in the heading).

**Still in need of implimentation** 

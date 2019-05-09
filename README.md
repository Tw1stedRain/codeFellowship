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

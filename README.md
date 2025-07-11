# AudioBooksAssignment

This is an Android Kotlin app that displays podcasts in a list, and allows navigation to a details page for more details

## Features
- Podcast Retrival
  - App makes a request to the sample API enpoint to retrieve podcasts. The endpoint however is a free mock, and therefore always returns the same 20 podcasts, and doesnt allow for paginating network calls.
  - <img width="326" height="726" alt="image" src="https://github.com/user-attachments/assets/c1b75888-0722-4dcd-872c-98aa37e093bc" />
- Pagination
  - Because the api endpoint doesnt allow for specific paginated network calls, all 20 are retrieved. I then created simulated pagination where only 10 are shown on the screen, with a button to load more at the bottom of the existing list. The button / text denoting the viewed items are flexible for any pagination size. With a different endpoint, I would use the button to launch another api request for more data
  - <img width="360" height="769" alt="image" src="https://github.com/user-attachments/assets/4c474567-a5cf-460e-af51-93ab47d51ee2" />
- Offline storage
  - Data is stored in a Room SQLlite DB in the event data cannot be retrieve from the api endpoint.
- Favourites
  - Favourite status is also persisted and is dynamic on both pages. Click the favorites button will toggle the status on the Podcast Details page, and also change the list in the home page to show the favourite labels in the list.
- Previews
  - All views have been created with previews that cover different states of the UI.
  - <img width="721" height="793" alt="image" src="https://github.com/user-attachments/assets/3838eebc-ef17-4560-9f50-f4227a412da2" />


## Project Structure
<img width="392" height="574" alt="image" src="https://github.com/user-attachments/assets/b614d4cf-63af-4197-a117-3ba0e4f8b4aa" />


## Libraries Used

- Room
  - Used or storage of podcast data in offline mode. Was also mentioned in the job posting as a preferred skill, so I decided to incorporate into this assignment
- OKHTTP
  - Used to make network requests to retrieve podcast Data
 
## Future Considerations

- Testing
  - UI tests would benefit the application to cover all error states in the network / db calls, as well as validate UI changes 
- UI polish
  - Various UI elements could be polished, such as adding animations, splash screns, and colour themes.
- Dependancy Injection
  - If this project were to scale out, I would definitely look at implementing Dependency Injection using Hilt to inject some of the more common dependencies like the repository

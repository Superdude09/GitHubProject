# GitHubProject

The overall architecture of this project follows the MVP pattern.  

The app has a single Activity with a few Fragments:
- SearchInputFragment: this contains the text input field and search button (no Presenter required)
- UserInfoFragment: this displays any data returned from the API calls to GitHub.  This Fragment does have a Presenter associated with it.
- UserRepoBottomSheetDialogFragment: this Fragment is the bottom sheet that appears when the user clicks on a repo to display the additional information.

The Fragments work together by taking the user input from SearchInputFragment and passing it to the Activity via a callback, then the Activity passes the input to UserInfoFragment (and in turn to UserInfoPresenter) to make the network call to retrieve the information from GitHub.  This avoids any direct Fragment-to-Fragment communication and keeps them decoupled.  Likewise, when a repo is clicked on in the RecyclerView, UserInfoFragment communicates passes the UserRepoInfo object to the Activity via a callback; the Activity then opens the UserRepoBottomSheetDialogFragment with the UserRepoInfo.

For network calls, Retrofit with RxJava is used.  
- If the Observables of either network calls is successful, the DTOs are mapped to the respective Model classes using a UserInfoProcesser object.  For example, the DTO UserInfoResponse object is mapped to a UserInfo object.  Currently for this project, the mapping is trivial, but in the future, if there is any further arbitrary mapping logic that is required, it can be added to the UserInfoProcesser implementation.  Please note that the UserInfoProcesser object is injected (using Dagger 2) into the Presenter to help faciliate decoupling and testability.
- If the Observables of either network calls emits an error, this will call hooks to the respective Fragments that can display inline errors, error dialogs, etc. as needed by future requirements.

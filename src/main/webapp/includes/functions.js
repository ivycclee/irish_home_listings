function ConfirmArchive(pId) {
    let confirmation = confirm(pId + " will be archived. Are you sure?");

    //if agent clicks OK, send req to Archive servlet with property id
    if(confirmation)
    {
        //need to set parameters so that id can be passed to servlet
        let url = window.location.href;
        let params = new URLSearchParams(url.search);
        params.set("id", pId);

        // console.log("Archive?" + params.toString());
        //redirect to servlet to process in db
        window.location.href = "Archive?" + params.toString();
    }
}

function AddToFav(userId, propertyId)
{
    //get all cookies
    var cookies = document.cookie.split("; ");

    //check if specific cookie exists
    if (cookies.find(c => c.startsWith(userId.toString())))
    {
        //if true, get value so that it can be amended
        var value = cookies.find(c => c.startsWith(userId.toString())).split("=")[1];
        // alert("Cookie exists " + value);

        //make sure property id is not already in cookie
        if (value.includes(propertyId)) {
            alert("Property already in favourites");
        }
        else {
            document.cookie = userId + "=" + value + propertyId + "-";
            alert("Added to favourites!");
        }
    }
    else {
        //otherwise, create a new cookie
        document.cookie = userId + "=" + propertyId + "-";
        alert("Added to favourites!");
    }



}

function RemoveFromArchive(listingNum) {
    let confirmation = confirm("This will be removed from archive and placed back to market. Are you sure?");

    //if agent confirms they want to place back into properties
    if (confirmation) {
        let url = window.location.href;
        let params = new URLSearchParams(url.search);
        params.set("listingNum", listingNum);

        //redirect to servlet to process in db
        window.location.href = "RemoveArchive?" + params.toString();
    }
}
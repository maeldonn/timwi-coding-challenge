export async function getAlbums(filter) {
    try {
        const reponse = await fetch(`${process.env.VUE_APP_ROOT_API}/albums?searchFilter=${filter}`, {
            method: 'GET'
        });
        return reponse.json();
    } catch (error) {
        console.error(error);
    }
}

export async function getPersonalAlbums() {
    try {
        const reponse = await fetch(`${process.env.VUE_APP_ROOT_API}/albums/personal`, {
            method: 'GET'
        });
        return reponse.json();
    } catch (error) {
        console.error(error);
    }
}

export async function addAlbumToPersonalList(id, callback) {
    try {
        await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
            method: 'POST'
        });
        callback();
    } catch (error) {
        console.error(error);
    }
}

export async function removeAlbumFromPersonalList(id, callback) {
    try {
        await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
            method: 'DELETE'
        });
        callback();
    } catch (error) {
        console.error(error);
    }
}

export async function toggleFavoriteAlbum(album, callback) {
    try {
        await fetch(`${process.env.VUE_APP_ROOT_API}/albums`, {
            method: 'PUT',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify(album)
        });
        callback();
    } catch (error) {
        console.error(error);
    }
}

export async function getAlbums(filter) {
    const reponse = await fetch(`${process.env.VUE_APP_ROOT_API}/albums?searchFilter=${filter}`, {
        method: 'GET'
    });
    return reponse.json();
}

export async function getPersonalAlbums() {
    const reponse = await fetch(`${process.env.VUE_APP_ROOT_API}/albums/personal`, {
        method: 'GET'
    });
    return reponse.json();
}

export async function addAlbumToPersonalList(id, callback) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
        method: 'POST'
    });
    callback();
}

export async function removeAlbumFromPersonalList(id, callback) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
        method: 'DELETE'
    });
    callback();
}

export async function toggleFavoriteAlbum(album, callback) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/albums`, {
        method: 'PUT',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify(album)
    });
    callback();
}

export async function getAlbums(filter) {
    const reponse = await fetch(`${process.env.VUE_APP_ROOT_API}/albums?searchFilter=${filter}`, {
        method: 'GET'
    });
    return reponse.json();
}

export async function addAlbumToPersonalList(id) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
        method: 'POST'
    });
}

export async function removeAlbumFromPersonalList(id) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/albums/${id}`, {
        method: 'DELETE'
    });
}

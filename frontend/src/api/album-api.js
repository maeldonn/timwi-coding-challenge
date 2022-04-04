import axios  from 'axios'

const axiosClient = axios.create({baseURL: `${process.env.VUE_APP_ROOT_API}/albums`});

export function getAlbums(filter, personal = false) {
    if (filter) {
        return axiosClient.get(`?searchQuery=${filter}&personal=${personal}`);
    } else {
        return axiosClient.get(`?personal=${personal}`);
    }
}

export function addAlbumToPersonalList(albumId) {
    return axiosClient.put(`/${albumId}`);
}

export function removeAlbumFromPersonalList(albumId) {
    return axiosClient.delete(`/${albumId}`);
}

export function updateAlbumFavorite(album) {
    return axiosClient.patch(`/${album.albumId}`, album);
}
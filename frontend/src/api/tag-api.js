import axios from 'axios'

const axiosClient = axios.create({baseURL: `${process.env.VUE_APP_ROOT_API}/tags`});

export function createTag(tag) {
    return axiosClient.post('/', tag);
}
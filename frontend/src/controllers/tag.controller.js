export async function createTag(tag, callback) {
    await fetch(`${process.env.VUE_APP_ROOT_API}/tags`, {
        method: 'POST',
        headers: {'content-type': 'application/json'},
        body: JSON.stringify(tag)
    });
    callback();
}

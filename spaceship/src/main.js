import * as THREE from 'three';

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(90, window.innerWidth / window.innerHeight, 0.1, 100);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);
const rotating_shapes = [];

let left_arrow_down = false;
let right_arrow_down = false;
let up_arrow_down = false;
let down_arrow_down = false;

// const plane_cube_side_len = 25;
// for (let x = 0; x < 100; x+=plane_cube_side_len) {
//     for (let y = 0; y < 100; y+=plane_cube_side_len ) {
//         const cube = create_green_mesh_cube(x, plane_cube_side_len, y);
//         rotating_shapes.push(cube);
//         scene.add(cube);
//     }
// }
// const cube = create_green_mesh_cube(1,1,1);
// rotating_shapes.push(cube);
// scene.add(cube);


function create_plane(plane_width, gap_between_lines, plane_depth, plane_y, plane_color) {
    const material = new THREE.LineBasicMaterial( {color: plane_color} );
    const num_z_lines = plane_width / gap_between_lines;
    for (let x = -1 * num_z_lines + 1; x < num_z_lines; x+= gap_between_lines) {
        const points = [];
        points.push (new THREE.Vector3(x,plane_y,-1));
        points.push (new THREE.Vector3(x,plane_y,plane_depth));
        const geometry = new THREE.BufferGeometry().setFromPoints(points);
        const line = new THREE.Line(geometry, material);
        scene.add(line);
    }
    const num_horizontal_lines = plane_depth / gap_between_lines;
    for (let z = 0; z < num_horizontal_lines; z += gap_between_lines) {
        const points = [];
        points.push (new THREE.Vector3(-1 * plane_width / 2,plane_y,z));
        points.push (new THREE.Vector3(plane_width / 2,plane_y,z));
        const geometry = new THREE.BufferGeometry().setFromPoints(points);
        const line = new THREE.Line(geometry, material);
        scene.add(line);
    }
}

create_plane(2000,2,2000,0, 0x00ff00);
create_plane(2000,2,2000,100, 0x00ff00);

camera.position.z = -2;
camera.position.y = 25;
camera.lookAt(0,0,1000);

function create_green_mesh_cube(x,y,z) {
    const geometry = new THREE.BoxGeometry( x, y, z );
    const material= new THREE.MeshBasicMaterial( { color: 0x00ffff, wireframe: true } );
    return new THREE.Mesh( geometry, material );
}

function rotate_shapes() {
    let x_rotation_delta = 0;
    let y_rotation_delta = 0;
    let z_rotation_delta = 0;
    if (left_arrow_down) {
        y_rotation_delta = 0.1;
    } else if (right_arrow_down) {
        y_rotation_delta = -0.1;
    }
    if (up_arrow_down) {
        z_rotation_delta = 0.1;
    } else if (down_arrow_down) {
        z_rotation_delta = -0.1;
    }
    for (let shape in rotating_shapes) {
        rotating_shapes[shape].rotation.x += x_rotation_delta;
        rotating_shapes[shape].rotation.y += y_rotation_delta;
        rotating_shapes[shape].rotation.z += z_rotation_delta;
    }
}
function update_info_label(message) {
    const info = document.getElementById("info");
    info.innerText = message;
}
function animate() {
    const camera_speed = 0.2;
    rotate_shapes();
    camera.position.z += 0.1;
    if (left_arrow_down) {
        camera.position.x -= camera_speed;
    } else if (right_arrow_down) {
        camera.position.x += camera_speed;
    }
    if (up_arrow_down && camera.position.y < 98) {
        camera.position.y += camera_speed;
    } else if (down_arrow_down && camera.position.y > 2) {
        camera.position.y -= camera_speed;
    }
    // update_info_label("camera y = " + camera.position.y);
    // cube.position.x = camera.position.x;
    // cube.position.y = camera.position.y;
    // cube.position.z = camera.position.z + 5;
    renderer.render( scene, camera );
}

renderer.setAnimationLoop(animate);

window.addEventListener("keydown", (event) => {
    switch (event.key) {
    case "ArrowLeft":
        right_arrow_down = false;
        left_arrow_down = true;
        break;
    case "ArrowRight":
        left_arrow_down = false;
        right_arrow_down = true;
        break;
    case "ArrowUp":
        down_arrow_down = false;
        up_arrow_down = true;
        break;
    case "ArrowDown":
        up_arrow_down = false;
        down_arrow_down = true;
        break;
    }
});

window.addEventListener("resize", (event) => {
    renderer.setSize(window.innerWidth, window.innerHeight);
})

window.addEventListener("keyup", (event) => {
    switch (event.key) {
    case "ArrowLeft":
        left_arrow_down = false;
        break;
    case "ArrowRight":
        right_arrow_down = false;
        break;
    case "ArrowUp":
        up_arrow_down = false;
        break;
    case "ArrowDown":
        down_arrow_down = false;
        break;
    }
});

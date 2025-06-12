import * as THREE from 'three';

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1,1000);
const renderer = new THREE.WebGLRenderer();
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);
const rotating_shapes = [];

let left_arrow_down = false;
let right_arrow_down = false;
let up_arrow_down = false;
let down_arrow_down = false;
let camera_speed = -0.2;

let camera_tilt = 0;

function create_plane(plane_width, gap_between_lines, plane_depth, plane_y, plane_color) {
    const material = new THREE.LineBasicMaterial( {color: plane_color} );
    const num_z_lines = plane_width / gap_between_lines;
    for (let x = (-1 * gap_between_lines * num_z_lines / 2)-1 ; x <= gap_between_lines * num_z_lines / 2; x+= gap_between_lines) {
        const points = [];
        points.push (new THREE.Vector3(x,plane_y,0));
        points.push (new THREE.Vector3(x,plane_y,plane_depth));
        const geometry = new THREE.BufferGeometry().setFromPoints(points);
        const line = new THREE.Line(geometry, material);
        scene.add(line);
    }
    for (let z = 0; z <= plane_depth; z += gap_between_lines) {
        const points = [];
        points.push (new THREE.Vector3(-1 * plane_width / 2,plane_y,z));
        points.push (new THREE.Vector3(plane_width / 2,plane_y,z));
        const geometry = new THREE.BufferGeometry().setFromPoints(points);
        const line = new THREE.Line(geometry, material);
        scene.add(line);
    }
}

create_plane(10000,50,10000,-200, 0x00ff00);
create_plane(10000,50,10000,200, 0x00ff00);

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
    rotate_shapes();
    // camera.rotation.z += 0.01;
    // camera.rotation.x += 0.01;
    // camera.rotation.y += 0.01;
    camera.position.z += camera_speed;
    if (left_arrow_down) {
        camera.position.x += 1;
    } else if (right_arrow_down) {
        camera.position.x -= 1;
    }
    // if (up_arrow_down && camera.position.y < 98) {
    if (up_arrow_down) {
        camera.position.y += 1;
    // } else if (down_arrow_down && camera.position.y > 2) {
    } else if (down_arrow_down) {
        camera.position.y -= 1;
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

window.addEventListener("wheel", (event) => {
    console.log(camera_speed);
    if (event.deltaY > 0) {
        camera_speed -= 0.01;
    } else {
        camera_speed += 0.01;
    }

});

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
